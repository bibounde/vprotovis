package com.bibounde.vprotovis.gwt.client.bar;

import java.util.logging.Logger;

import com.bibounde.pcharts.client.bar.BarChartWidget;
import com.bibounde.pcharts.shared.bar.BarJSModel;
import com.bibounde.pcharts.shared.bar.BarJSModelImpl;
import com.bibounde.vprotovis.gwt.client.VaadinTooltipCSSNames;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VBarChartComponent extends BarChartWidget implements Paintable {

    public static final String UIDL_BAR_JSMODEL = "vprotovis.bar.jsmodel";
    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-barchart";
    
    private Logger LOGGER = Logger.getLogger(VBarChartComponent.class.getName());
    
    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;
    
    protected UIDL currentUIDL;
    
    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public VBarChartComponent() {
        super();
        //TODO: classname
        this.setTooltipCSSNames(new VaadinTooltipCSSNames());
    }
    
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (client.updateComponent(this, uidl, true)) {
            // If client.updateComponent returns true there has been no changes and we
            // do not need to update anything.
            return;
        }
        
        this.currentUIDL = uidl;

        String jsonModel = this.currentUIDL.getStringVariable(UIDL_BAR_JSMODEL);
        this.model = this.getBarJSModel(jsonModel);
        
        // Save reference to server connection object to be able to send
        // user interaction later
        this.client = client;

        // Save the client side identifier (paintable id) for the widget
        paintableId = uidl.getId();
        getElement().setId(this.getDivId());
        
        this.render();
    }
    
    private BarJSModel getBarJSModel(String jsModelAsString) {
        BarJSModel ret = new BarJSModelImpl();
        
        JSONValue value = JSONParser.parseStrict(jsModelAsString);
        JSONObject barJSmodelObject = value.isObject();
        
        ret.setId(barJSmodelObject.get("id").isString().stringValue());
        ret.setWidth(barJSmodelObject.get("width").isNumber().doubleValue());
        ret.setHeight(barJSmodelObject.get("height").isNumber().doubleValue());
        
        //Serie names
        JSONArray serieNames = barJSmodelObject.get("serieNames").isArray();
        ret.setSerieNames(new String[serieNames.size()]);
        for (int i = 0; i < serieNames.size(); i++) {
            ret.getSerieNames()[i] = serieNames.get(i).isString().stringValue();
        }
        
        //Group names
        JSONArray groupNames = barJSmodelObject.get("groupNames").isArray();
        ret.setGroupNames(new String[groupNames.size()]);
        for (int i = 0; i < groupNames.size(); i++) {
            ret.getGroupNames()[i] = groupNames.get(i).isString().stringValue();
        }
        
        //Group values
        JSONArray groupValues = barJSmodelObject.get("groupValues").isArray();
        ret.setGroupValues(new double[groupValues.size()][]);
        for (int i = 0; i < groupValues.size(); i++) {
            JSONArray groupValue = groupValues.get(i).isArray();
            ret.getGroupValues()[i] = new double[groupValue.size()];
            for (int j = 0; j < groupValue.size(); j++) {
                ret.getGroupValues()[i][j] = groupValue.get(j).isNumber().doubleValue();
            }
        }
        
        //Panel infos
        ret.setPanelBottom(barJSmodelObject.get("panelBottom").isNumber().doubleValue());
        ret.setPanelLeft(barJSmodelObject.get("panelLeft").isNumber().doubleValue());
        
        //Group infos
        ret.setGroupWidth(barJSmodelObject.get("groupWidth").isNumber().doubleValue());
        ret.setGroupInset(barJSmodelObject.get("groupInset").isNumber().doubleValue());
        
        //Bar infos
        ret.setBarHeight(barJSmodelObject.get("barHeight").isNumber().doubleValue());
        ret.setBarInset(barJSmodelObject.get("barInset").isNumber().doubleValue());
        ret.setBarWidth(barJSmodelObject.get("barWidth").isNumber().doubleValue());
        
        //Padding info
        ret.setPaddingBottom(barJSmodelObject.get("paddingBottom").isNumber().doubleValue());
        ret.setPaddingLeft(barJSmodelObject.get("paddingLeft").isNumber().doubleValue());
        ret.setPaddingRight(barJSmodelObject.get("paddingRight").isNumber().doubleValue());
        ret.setPaddingTop(barJSmodelObject.get("paddingTop").isNumber().doubleValue());
        
        //Axis
        ret.setXAxisEnabled(barJSmodelObject.get("isXAxisEnabled").isBoolean().booleanValue());
        ret.setXAxisLabelEnabled(barJSmodelObject.get("isXAxisLabelEnabled").isBoolean().booleanValue());
        ret.setYAxisEnabled(barJSmodelObject.get("isYAxisEnabled").isBoolean().booleanValue());
        ret.setYAxisLabelEnabled(barJSmodelObject.get("isYAxisLabelEnabled").isBoolean().booleanValue());
        ret.setYAxisGridEnabled(barJSmodelObject.get("isYAxisGridEnabled").isBoolean().booleanValue());
        
        //Labels
        JSONArray yAxisLabelRangeValues = barJSmodelObject.get("yAxisLabelRangeValues").isArray();
        ret.setYAxisLabelRangeValues(new double[yAxisLabelRangeValues.size()]);
        for (int i = 0; i < yAxisLabelRangeValues.size(); i++) {
            ret.getYAxisLabelRangeValues()[i] = yAxisLabelRangeValues.get(i).isNumber().doubleValue();
        }
        JSONArray yAxisLabelRangeTextValues = barJSmodelObject.get("yAxisLabelRangeTextValues").isArray();
        ret.setYAxisLabelRangeTextValues(new String[yAxisLabelRangeTextValues.size()]);
        for (int i = 0; i < yAxisLabelRangeTextValues.size(); i++) {
            ret.getYAxisLabelRangeTextValues()[i] = yAxisLabelRangeTextValues.get(i).isString().stringValue();
        }
        
        //Tooltip
        JSONArray groupTooltipValues = barJSmodelObject.get("groupTooltipValues").isArray();
        ret.setGroupTooltipValues(new String[groupTooltipValues.size()][]);
        for (int i = 0; i < groupTooltipValues.size(); i++) {
            JSONArray groupTooltipValue = groupTooltipValues.get(i).isArray();
            ret.getGroupTooltipValues()[i] = new String[groupTooltipValue.size()];
            for (int j = 0; j < groupTooltipValue.size(); j++) {
                ret.getGroupTooltipValues()[i][j] = groupTooltipValue.get(j).isString().stringValue();
            }
        }
        ret.setTooltipEnabled(barJSmodelObject.get("tooltipEnabled").isBoolean().booleanValue());
        
        //Margin
        ret.setMarginBottom(barJSmodelObject.get("marginBottom").isNumber().doubleValue());
        ret.setMarginLeft(barJSmodelObject.get("marginLeft").isNumber().doubleValue());
        ret.setMarginRight(barJSmodelObject.get("marginRight").isNumber().doubleValue());
        ret.setMarginTop(barJSmodelObject.get("marginTop").isNumber().doubleValue());
        
        //Legend
        ret.setLegendAreaWidth(barJSmodelObject.get("legendAreaWidth").isNumber().doubleValue());
        ret.setLegendEnabled(barJSmodelObject.get("legendEnabled").isBoolean().booleanValue());
        ret.setLegendInsetLeft(barJSmodelObject.get("legendInsetLeft").isNumber().doubleValue());
        
        //Colors
        JSONArray colors = barJSmodelObject.get("colors").isArray();
        ret.setColors(new String[colors.size()]);
        for (int i = 0; i < colors.size(); i++) {
            ret.getColors()[i] = colors.get(i).isString().stringValue();
        }
        
        return ret;
    }
}
