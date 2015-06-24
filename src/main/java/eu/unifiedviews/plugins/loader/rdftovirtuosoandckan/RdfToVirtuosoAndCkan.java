package eu.unifiedviews.plugins.loader.rdftovirtuosoandckan;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

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
import eu.unifiedviews.plugins.loader.rdftockan.RdfToCkan;
import eu.unifiedviews.plugins.loader.rdftockan.RdfToCkanConfig_V1;
import eu.unifiedviews.plugins.loader.rdftovirtuoso.RdfToVirtuoso;
import eu.unifiedviews.plugins.loader.rdftovirtuoso.RdfToVirtuosoConfig_V1;

@DPU.AsLoader
public class RdfToVirtuosoAndCkan extends AbstractDpu<RdfToVirtuosoAndCkanConfig_V1> {

    private static final Logger LOG = LoggerFactory.getLogger(RdfToVirtuosoAndCkan.class);

    @DataUnit.AsInput(name = "rdfInput")
    public RDFDataUnit rdfInput;

    @DataUnit.AsInput(name = "distributionInput", optional = true)
    public RDFDataUnit distributionInput;

    @DataUnit.AsOutput(name = "rdfIntermediate")
    public WritableRDFDataUnit rdfIntermediate;

    public static final String CONFIGURATION_DATASET_URI_PATTERN = "dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern";

    public RdfToVirtuosoAndCkan() {
        super(RdfToVirtuosoAndCkanVaadinDialog.class, ConfigHistory.noHistory(RdfToVirtuosoAndCkanConfig_V1.class));
    }

    @Override
    protected void innerExecute() throws DPUException {
        DPUContext dpuContext = ctx.getExecMasterContext().getDpuContext();
        Map<String, String> environment = dpuContext.getEnvironment();

        String secretToken = environment.get(RdfToCkan.CONFIGURATION_SECRET_TOKEN);
        if (environment.get(RdfToCkan.CONFIGURATION_SECRET_TOKEN) == null || environment.get(RdfToCkan.CONFIGURATION_SECRET_TOKEN).isEmpty()) {
            throw ContextUtils.dpuException(ctx, "RdfToCkan.execute.exception.missingSecretToken");
        }
        String userId = (dpuContext.getPipelineExecutionOwnerExternalId() != null) ? dpuContext.getPipelineExecutionOwnerExternalId()
                : dpuContext.getPipelineExecutionOwner();
        String pipelineId = String.valueOf(dpuContext.getPipelineId());

        String catalogApiLocation = environment.get(RdfToCkan.CONFIGURATION_CATALOG_API_LOCATION);
        if (catalogApiLocation == null || catalogApiLocation.isEmpty()) {
            throw ContextUtils.dpuException(ctx, "RdfToCkan.execute.exception.missingCatalogApiLocation");
        }
        String datasetUriPattern = environment.get(CONFIGURATION_DATASET_URI_PATTERN);

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

        JsonObject dataset = rdfToCkan.packageShow(catalogApiLocation, pipelineId, userId, secretToken, additionalHttpHeaders);
        String datasetName = dataset.getJsonObject("result").getString("name");
        String datasetUri = MessageFormat.format(datasetUriPattern, datasetName);

        RdfToVirtuosoConfig_V1 rdfToVirtuosoConfig = new RdfToVirtuosoConfig_V1();
        rdfToVirtuosoConfig.setVirtuosoUrl(null);
        rdfToVirtuosoConfig.setUsername(null);
        rdfToVirtuosoConfig.setPassword(null);
        rdfToVirtuosoConfig.setClearDestinationGraph(true);
        rdfToVirtuosoConfig.setTargetGraphName(datasetUri);
        rdfToVirtuosoConfig.setThreadCount(1);
        rdfToVirtuosoConfig.setSkipOnError(true);
        rdfToVirtuoso.rdfInput = rdfInput;
        rdfToVirtuoso.rdfOutput = rdfIntermediate;
        rdfToVirtuoso.outerExecute(ctx, rdfToVirtuosoConfig);

        rdfToCkan.rdfInput = rdfIntermediate;
        rdfToCkan.distributionInput = distributionInput;
        rdfToCkan.outerExecute(ctx, new RdfToCkanConfig_V1());
    }

}
