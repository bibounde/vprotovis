package com.bibounde.vprotovis.gwt.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public abstract class VAbstractChartComponent extends Widget implements Paintable {
    
    public static final String UIDL_DIV_ID = "vprotovis.div.id";
    public static final String UIDL_OPTIONS_WIDTH = "vprotovis.options.width";
    public static final String UIDL_OPTIONS_HEIGHT = "vprotovis.options.height";
    public static final String UIDL_OPTIONS_MARGIN_LEFT = "vprotovis.options.margin.left";
    public static final String UIDL_OPTIONS_MARGIN_RIGHT = "vprotovis.options.margin.right";
    public static final String UIDL_OPTIONS_MARGIN_TOP= "vprotovis.options.margin.top";
    public static final String UIDL_OPTIONS_MARGIN_BOTTOM = "vprotovis.options.margin.bottom";
    public static final String UIDL_OPTIONS_COLORS = "vprotovis.options.colors";
    public static final String UIDL_OPTIONS_LEGEND_ENABLED = "vprotovis.options.legend.enabled";
    public static final String UIDL_OPTIONS_LEGEND_AREA_WIDTH = "vprotovis.options.legend.area.width";
    public static final String UIDL_OPTIONS_LEGEND_INSET_LEFT = "vprotovis.options.legend.inset.left";
    public static final String UIDL_OPTIONS_TOOLTIP_ENABLED = "vprotovis.options.tooltip.enabled";
    
    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;
    
    protected UIDL currentUIDL;
    
    
    public VAbstractChartComponent() {
        DivElement canvas = Document.get().createDivElement();
        setElement(canvas);
        setStyleName(this.getClassName());
    }
    
    public abstract String getClassName();

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (client.updateComponent(this, uidl, true)) {
            // If client.updateComponent returns true there has been no changes and we
            // do not need to update anything.
            return;
        }
        
        this.currentUIDL = uidl;

        // Save reference to server connection object to be able to send
        // user interaction later
        this.client = client;

        // Save the client side identifier (paintable id) for the widget
        paintableId = uidl.getId();
        getElement().setId(this.getDivId());
        
        this.render();
    }
    
    public native void render()/*-{
    }-*/;
    
    public String getDivId() {
        return this.currentUIDL.getStringVariable(UIDL_DIV_ID);
    }
    
    public double getChartWidth() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_WIDTH);
    }
    
    public double getChartHeight() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_HEIGHT);
    }
    
    public double getMarginLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_MARGIN_LEFT);
    }
    
    public double getMarginRight() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_MARGIN_RIGHT);
    }

    public double getMarginTop() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_MARGIN_TOP);
    }
    
    public double getMarginBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_MARGIN_BOTTOM);
    }
    
    public boolean isLegendEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_LEGEND_ENABLED);
    }
    
    public double getLegendAreaWidth() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEGEND_AREA_WIDTH);
    }
    
    public String getColors() {
        String[] colors = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_COLORS);
        
        StringBuilder ret = new StringBuilder("$wnd.pv.colors(");

        for (int i = 0; i < colors.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append("'").append(colors[i]).append("'");
        }
        ret.append(")");
        return ret.toString();
    }
    
    public boolean isTooltipEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIP_ENABLED);
    }
    
    public double getLegendInsetLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEGEND_INSET_LEFT);
    }

}
