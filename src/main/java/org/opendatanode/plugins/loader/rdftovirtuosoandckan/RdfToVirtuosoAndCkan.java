package org.opendatanode.plugins.loader.rdftovirtuosoandckan;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.opendatanode.plugins.loader.rdftockan.RdfToCkan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.unifiedviews.dataunit.DataUnit;
import eu.unifiedviews.dataunit.rdf.RDFDataUnit;
import eu.unifiedviews.dataunit.rdf.WritableRDFDataUnit;
import eu.unifiedviews.dpu.DPU;
import eu.unifiedviews.dpu.DPUContext;
import eu.unifiedviews.dpu.DPUException;
import eu.unifiedviews.helpers.dpu.config.ConfigHistory;
import eu.unifiedviews.helpers.dpu.context.ContextUtils;
import eu.unifiedviews.helpers.dpu.exec.AbstractDpu;
import eu.unifiedviews.plugins.loader.rdftockan.RdfToCkanConfig_V1;
import eu.unifiedviews.plugins.loader.rdftovirtuoso.RdfToVirtuoso;
import eu.unifiedviews.plugins.loader.rdftovirtuoso.RdfToVirtuosoConfig_V1;
import eu.unifiedviews.plugins.loader.rdftovirtuosoandckan.RdfToVirtuosoAndCkanConfig_V1;

@DPU.AsLoader
public class RdfToVirtuosoAndCkan extends AbstractDpu<RdfToVirtuosoAndCkanConfig_V1> {

    private static final Logger LOG = LoggerFactory.getLogger(RdfToVirtuosoAndCkan.class);

    @DataUnit.AsInput(name = "rdfInput")
    public RDFDataUnit rdfInput;

    @DataUnit.AsInput(name = "distributionInput", optional = true)
    public RDFDataUnit distributionInput;

    @DataUnit.AsOutput(name = "rdfIntermediate", optional = true)
    public WritableRDFDataUnit rdfIntermediate;

    public static final String CONFIGURATION_DATASET_URI_PATTERN = "dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern";

    public static final String CONFIGURATION_RESOURCE_NAME = "dpu.uv-l-rdfToVirtuosoAndCkan.resource.name";

    public static final String DATASET_URI_ID_PLACEHOLDER = "ckan_package_id";

    public static final String DATASET_URI_NAME_PLACEHOLDER = "ckan_package_name";

    public RdfToVirtuosoAndCkan() {
        super(RdfToVirtuosoAndCkanVaadinDialog.class, ConfigHistory.noHistory(RdfToVirtuosoAndCkanConfig_V1.class));
    }

    @Override
    protected void innerExecute() throws DPUException {
        DPUContext dpuContext = ctx.getExecMasterContext().getDpuContext();
        Map<String, String> environment = dpuContext.getEnvironment();

        String secretToken = environment.get(RdfToCkan.CONFIGURATION_SECRET_TOKEN);
        if (StringUtils.isEmpty(secretToken)) {
            throw ContextUtils.dpuException(ctx, "RdfToCkan.execute.exception.missingSecretToken");
        }
        String userId = (dpuContext.getPipelineExecutionOwnerExternalId() != null) ? dpuContext.getPipelineExecutionOwnerExternalId()
                : dpuContext.getPipelineExecutionOwner();
        String pipelineId = String.valueOf(dpuContext.getPipelineId());

        String catalogApiLocation = environment.get(RdfToCkan.CONFIGURATION_CATALOG_API_LOCATION);
        if (StringUtils.isEmpty(catalogApiLocation)) {
            throw ContextUtils.dpuException(ctx, "RdfToCkan.execute.exception.missingCatalogApiLocation");
        }
        String datasetUriPattern = environment.get(CONFIGURATION_DATASET_URI_PATTERN);

        String resourceName = environment.get(CONFIGURATION_RESOURCE_NAME);

        Map<String, String> additionalHttpHeaders = new HashMap<>();
        for (Map.Entry<String, String> configEntry : environment.entrySet()) {
            if (configEntry.getKey().startsWith(RdfToCkan.CONFIGURATION_HTTP_HEADER)) {
                String headerName = configEntry.getKey().replace(RdfToCkan.CONFIGURATION_HTTP_HEADER, "");
                String headerValue = configEntry.getValue();
                additionalHttpHeaders.put(headerName, headerValue);
            }
        }

        RdfToCkan rdfToCkan = new RdfToCkan();
        RdfToVirtuoso rdfToVirtuoso = new RdfToVirtuoso();

        JsonObject dataset = rdfToCkan.packageShow(ctx, catalogApiLocation, pipelineId, userId, secretToken, additionalHttpHeaders);
        Map<String, String> args = new HashMap<>();
        args.put(DATASET_URI_ID_PLACEHOLDER, dataset.getJsonObject("result").getString("id"));
        args.put(DATASET_URI_NAME_PLACEHOLDER, dataset.getJsonObject("result").getString("name"));
        StrSubstitutor sub = new StrSubstitutor(args);
        String datasetUri = sub.replace(datasetUriPattern);

        RdfToVirtuosoConfig_V1 rdfToVirtuosoConfig = new RdfToVirtuosoConfig_V1();
        rdfToVirtuosoConfig.setVirtuosoUrl(null);
        rdfToVirtuosoConfig.setUsername(null);
        rdfToVirtuosoConfig.setPassword(null);
        rdfToVirtuosoConfig.setClearDestinationGraph(config.isClearDestinationGraph());
        rdfToVirtuosoConfig.setTargetGraphName(datasetUri);
        rdfToVirtuosoConfig.setThreadCount(1);
        rdfToVirtuosoConfig.setSkipOnError(true);
        rdfToVirtuoso.rdfInput = rdfInput;
        rdfToVirtuoso.rdfOutput = rdfIntermediate;
        rdfToVirtuoso.outerExecute(ctx, rdfToVirtuosoConfig);

        rdfToCkan.rdfInput = rdfIntermediate;
        rdfToCkan.distributionInput = distributionInput;
        RdfToCkanConfig_V1 rdfToCkanConfig = new RdfToCkanConfig_V1();
        rdfToCkanConfig.setResourceName(resourceName);
        rdfToCkan.outerExecute(ctx, rdfToCkanConfig);
    }
}
