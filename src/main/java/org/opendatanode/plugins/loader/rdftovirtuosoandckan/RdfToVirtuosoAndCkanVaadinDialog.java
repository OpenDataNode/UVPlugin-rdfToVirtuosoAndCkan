package org.opendatanode.plugins.loader.rdftovirtuosoandckan;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;

import eu.unifiedviews.dpu.config.DPUConfigException;
import eu.unifiedviews.helpers.dpu.vaadin.dialog.AbstractDialog;
import eu.unifiedviews.plugins.loader.rdftovirtuosoandckan.RdfToVirtuosoAndCkanConfig_V1;

/**
 * DPU's configuration dialog. User can use this dialog to configure DPU
 * configuration.
 */
public class RdfToVirtuosoAndCkanVaadinDialog extends AbstractDialog<RdfToVirtuosoAndCkanConfig_V1> {

    private static final long serialVersionUID = -5666909428L;

    private ObjectProperty<Boolean> clearDestinationGraph = new ObjectProperty<Boolean>(false);

    public RdfToVirtuosoAndCkanVaadinDialog() {
        super(RdfToVirtuosoAndCkan.class);
    }

    @Override
    protected void buildDialogLayout() {
        setSizeFull();
        FormLayout mainLayout = new FormLayout();

        // top-level component properties
        setWidth("100%");
        setHeight("100%");
        mainLayout.addComponent(new CheckBox(ctx.tr("RdfToVirtuosoVaadinDialog.clearDestinationGraph"), clearDestinationGraph));
        setCompositionRoot(mainLayout);
    }

    @Override
    public void setConfiguration(RdfToVirtuosoAndCkanConfig_V1 conf) throws DPUConfigException {
        clearDestinationGraph.setValue(conf.isClearDestinationGraph());
    }

    @Override
    public RdfToVirtuosoAndCkanConfig_V1 getConfiguration() throws DPUConfigException {
        RdfToVirtuosoAndCkanConfig_V1 conf = new RdfToVirtuosoAndCkanConfig_V1();
        conf.setClearDestinationGraph(clearDestinationGraph.getValue());
        return conf;
    }

}
