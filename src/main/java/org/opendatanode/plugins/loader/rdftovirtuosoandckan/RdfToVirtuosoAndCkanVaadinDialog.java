package org.opendatanode.plugins.loader.rdftovirtuosoandckan;

import eu.unifiedviews.dpu.config.DPUConfigException;
import eu.unifiedviews.helpers.dpu.vaadin.dialog.AbstractDialog;
import eu.unifiedviews.plugins.loader.rdftockan.RdfToCkanVaadinDialog;
import eu.unifiedviews.plugins.loader.rdftovirtuoso.RdfToVirtuosoVaadinDialog;
import eu.unifiedviews.plugins.loader.rdftovirtuosoandckan.RdfToVirtuosoAndCkanConfig_V1;

/**
 * DPU's configuration dialog. User can use this dialog to configure DPU
 * configuration.
 */
public class RdfToVirtuosoAndCkanVaadinDialog extends AbstractDialog<RdfToVirtuosoAndCkanConfig_V1> {

    private static final long serialVersionUID = -5666909428L;


    public RdfToVirtuosoAndCkanVaadinDialog() {
        super(RdfToVirtuosoAndCkan.class);
    }

    @Override
    protected void buildDialogLayout() {
    }

    @Override
    public void setConfiguration(RdfToVirtuosoAndCkanConfig_V1 conf) throws DPUConfigException {
    }

    @Override
    public RdfToVirtuosoAndCkanConfig_V1 getConfiguration() throws DPUConfigException {
        RdfToVirtuosoAndCkanConfig_V1 conf = new RdfToVirtuosoAndCkanConfig_V1();
        return conf;
    }

}
