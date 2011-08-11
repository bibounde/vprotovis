package com.bibounde.vprotovis.gwt.client.line;

import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.UIDLUtil;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VLineChartComponent extends Widget implements Paintable {

    public static final String UIDL_DIV_ID = "vprotovis.div.id";
    public static final String UIDL_DATA_SERIES_NAMES = "vprotovis.data.series.names";
    public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
    public static final String UIDL_DATA_SERIE_VALUES = "vprotovis.data.serie.values.";
    public static final String UIDL_OPTIONS_WIDTH = "vprotovis.options.width";
    public static final String UIDL_OPTIONS_HEIGHT = "vprotovis.options.height";
    public static final String UIDL_OPTIONS_BOTTOM = "vprotovis.options.bottom";
    public static final String UIDL_OPTIONS_LEFT = "vprotovis.options.left";
    public static final String UIDL_OPTIONS_LINE_BOTTOM = "vprotovis.options.line.bottom";
    public static final String UIDL_OPTIONS_LINE_LEFT = "vprotovis.options.line.left";
    public static final String UIDL_OPTIONS_MARGIN_LEFT = "vprotovis.options.margin.left";
    public static final String UIDL_OPTIONS_MARGIN_RIGHT = "vprotovis.options.margin.right";
    public static final String UIDL_OPTIONS_MARGIN_TOP= "vprotovis.options.margin.top";
    public static final String UIDL_OPTIONS_MARGIN_BOTTOM = "vprotovis.options.margin.bottom";
    public static final String UIDL_OPTIONS_PADDING_LEFT = "vprotovis.options.padding.left";
    public static final String UIDL_OPTIONS_PADDING_RIGHT = "vprotovis.options.padding.right";
    public static final String UIDL_OPTIONS_PADDING_TOP= "vprotovis.options.padding.top";
    public static final String UIDL_OPTIONS_PADDING_BOTTOM = "vprotovis.options.padding.bottom";
    public static final String UIDL_OPTIONS_HORIZONTAL_AXIS_ENABLED = "vprotovis.options.horizontal.axis.enabled";
    public static final String UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_ENABLED = "vprotovis.options.horizontal.axis.label.enabled";
    public static final String UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_D_VALUES = "vprotovis.options.horizontal.axis.label.range.d.values";
    public static final String UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_S_VALUES = "vprotovis.options.horizontal.axis.label.range.s.values";
    public static final String UIDL_OPTIONS_HORIZONTAL_AXIS_GRID_ENABLED = "vprotovis.options.horizontal.axis.grid.enabled";
    public static final String UIDL_OPTIONS_VERTICAL_AXIS_ENABLED = "vprotovis.options.vertical.axis.enabled";
    public static final String UIDL_OPTIONS_VERTICAL_AXIS_LABEL_ENABLED = "vprotovis.options.vertical.axis.label.enabled";
    public static final String UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_D_VALUES = "vprotovis.options.vertical.axis.label.range.d.values";
    public static final String UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_S_VALUES = "vprotovis.options.vertical.axis.label.range.s.values";
    public static final String UIDL_OPTIONS_VERTICAL_AXIS_GRID_ENABLED = "vprotovis.options.vertical.axis.grid.enabled";
    public static final String UIDL_OPTIONS_INTERPOLATION_MODE = "vprotovis.options.interpolation.mode";
    public static final String UIDL_OPTIONS_LINE_WIDTH = "vprotovis.options.line.width";
    public static final String UIDL_OPTIONS_COLORS = "vprotovis.options.colors";
    public static final String UIDL_OPTIONS_LEGEND_ENABLED = "vprotovis.options.legend.enabled";
    public static final String UIDL_OPTIONS_LEGEND_AREA_WIDTH = "vprotovis.options.legend.area.width";

    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-linechart";

    private Logger LOGGER = Logger.getLogger(VLineChartComponent.class.getName());

    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;

    private UIDL currentUIDL;

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public VLineChartComponent() {
        DivElement canvas = Document.get().createDivElement();
        setElement(canvas);
        setStyleName(CLASSNAME);
    }

    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {

        if (client.updateComponent(this, uidl, true)) {
            // If client.updateComponent returns true there has been no changes
            // and we
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

        execChart();
    }

    private native void execChart() /*-{
        var vlinechart = this;
        
        var colors = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getColors()());
        var axisColor = $wnd.pv.color("#969696");
        var legendColor = $wnd.pv.color("#464646");
        var gridColor = $wnd.pv.color("#E4E4E4");
        
        var data = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getData()());
        var serieNames = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getSerieNames()());

        var chartWidth = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getChartWidth()();
        var chartHeight = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getChartHeight()();
        
        var marginLeft = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getMarginLeft()();
        var marginRight = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getMarginRight()();
        var marginBottom = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getMarginBottom()();
        var marginTop = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getMarginTop()();
        var paddingLeft = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPaddingLeft()();
        var paddingRight = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPaddingRight()();
        var paddingBottom = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPaddingBottom()();
        var paddingTop = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPaddingTop()();
        
        var legendAreaWidth = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getLegendAreaWidth()();
        
        var maxYTick = chartHeight - marginTop;
        var minYTick = marginBottom;
        var maxXTick = chartWidth - marginRight - legendAreaWidth;
        var minXTick = marginLeft;
        
        var xRange = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getHorizontalAxisLabelRangeDValues()());
        var xRangeText = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getHorizontalAxisLabelRangeSValues()());
        
        var yRange = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getVerticalAxisLabelRangeDValues()());
        var yRangeText = eval(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getVerticalAxisLabelRangeSValues()());
        
        var panelBottom = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPanelBottom()();
        var panelLeft = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getPanelLeft()();
        
        var vis = new $wnd.pv.Panel().canvas(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getDivId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
        
        //Grid management
        if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isHorizontalAxisGridEnabled()()) {
            var grid = vis.add($wnd.pv.Rule).data(xRange);
            grid.left(function(d) {
               var ret = (d * lineLeft) + panelLeft;
               if (ret <= maxXTick && ret >= minXTick) {
                   return ret;
               } else {
                   //Out of range
                   return chartWidth * 10;
               }
            });
            grid.bottom(0 + marginBottom);
            grid.height(chartHeight - marginBottom - marginTop);
            grid.strokeStyle(gridColor);
        }
        
        if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isVerticalAxisGridEnabled()()) {
            var grid = vis.add($wnd.pv.Rule).data(yRange);
            grid.bottom(function(d) {
                var ret = (d * lineBottom) + panelBottom;
                if (ret <= maxYTick && ret >= minYTick) {
                    return ret;
                } else {
                    //Out of range
                    return chartHeight * 10;
                }
            });
            grid.left(0 + marginLeft);
            grid.width(chartWidth - marginLeft - marginRight - legendAreaWidth);
            grid.strokeStyle(gridColor);
        }
        
        //Horizontal axis management
        if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isHorizontalAxisEnabled()()) {
        
            var rule = vis.add($wnd.pv.Rule);
            
            rule.bottom(panelBottom).width(chartWidth - marginLeft - marginRight - legendAreaWidth).left(0 + marginLeft);
            rule.strokeStyle(axisColor);
        
            if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isHorizontalAxisLabelEnabled()()) {
        
                var tick = vis.add($wnd.pv.Rule).data(xRange);
                tick.left(function(d) { 
                    var ret = (d * lineLeft) + panelLeft;
                    if (ret <= maxXTick && ret >= minXTick) {
                        return ret;
                    } else {
                        //Out of range
                        return chartWidth * 10;
                    }
                });
                tick.bottom(panelBottom - 3);
                tick.height(3);
                tick.strokeStyle(axisColor);
                tick.anchor("bottom").add($wnd.pv.Label).text(function() {
                    return xRangeText[this.index];
                }).textStyle(axisColor);
            }    
        }
        
        //Vertical axis management
        if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isVerticalAxisEnabled()()) {
            var rule = vis.add($wnd.pv.Rule);
            rule.bottom(0 + marginBottom).left(panelLeft).height(chartHeight - marginBottom - marginTop);
            rule.strokeStyle(axisColor);
            
            if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isVerticalAxisLabelEnabled()()) {
        
                var tick = vis.add($wnd.pv.Rule).data(yRange);
                tick.bottom(function(d) {
                    var ret = (d * lineBottom) + panelBottom;
                    if (ret <= maxYTick && ret >= minYTick) {
                        return ret;
                    } else {
                        //Out of range
                        return chartHeight * 10;
                    }
                });
                tick.left(panelLeft - 3);
                tick.width(3);
                tick.strokeStyle(axisColor);
                tick.anchor("left").add($wnd.pv.Label).text(function() {
                    return yRangeText[this.index];
                }).textStyle(axisColor);
            }
        }
        
        var panel = vis.add($wnd.pv.Panel).data(data).bottom(panelBottom).left(panelLeft); 
        var line = panel.add($wnd.pv.Line).data(function(d) {return d;})
        
        var lineBottom = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getLineBottom()();
        var lineLeft = this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getLineLeft()();
        
        line.left(function(d) {return d.x * lineLeft;});
        line.bottom(function(d) {return d.y * lineBottom;});
        line.lineWidth(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getLineWidth()());
        line.interpolate(this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::getInterpolationMode()());
        line.strokeStyle(function() {return colors.range()[this.parent.index];});
        
        //Legend management
        if (this.@com.bibounde.vprotovis.gwt.client.line.VLineChartComponent::isLegendEnabled()) {
            //Use bar instead of DOT because msie-shim does not support it
            var legend = vis.add($wnd.pv.Bar).data(serieNames);
            legend.top(function(){
                return marginTop + (this.index * 18);
            });
            //Offset = 20
            legend.width(11).height(11).left(chartWidth - marginRight - legendAreaWidth + 20);
            legend.fillStyle(colors.by($wnd.pv.index));
            legend.anchor("left").add($wnd.pv.Label).textBaseline("middle").textMargin(16).textStyle(legendColor);
        }
        
        vis.render();
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

    public double getPanelBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BOTTOM);
    }
    
    public double getPanelLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEFT);
    }

    public double getLineBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LINE_BOTTOM);
    }

    public double getLineLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LINE_LEFT);
    }

    public String getData() {
        int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_SERIES_COUNT);

        StringBuilder ret = new StringBuilder("[");

        for (int i = 0; i < serieCount; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append("[");
            String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIE_VALUES + i);
            for (int j = 0; j < values.length; j++) {
                if (j > 0) {
                    ret.append(", ");
                }
                String[] coord = values[j].split("\\|");
                ret.append("{x:").append(coord[0]).append(",y:").append(coord[1]).append("}");
            }
            ret.append("]");
        }

        ret.append("]");

        return ret.toString();
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
    
    public boolean isHorizontalAxisEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_HORIZONTAL_AXIS_ENABLED);
    }
    
    public boolean isHorizontalAxisLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_ENABLED);
    }
    
    public boolean isHorizontalAxisGridEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_HORIZONTAL_AXIS_GRID_ENABLED);
    }
    
    public String getHorizontalAxisLabelRangeDValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_D_VALUES);
        return UIDLUtil.getJSArray(values, false);
    }
    
    public String getHorizontalAxisLabelRangeSValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_S_VALUES);
        return UIDLUtil.getJSArray(values, true);
    }

    public boolean isVerticalAxisEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_VERTICAL_AXIS_ENABLED);
    }
    
    public boolean isVerticalAxisLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_VERTICAL_AXIS_LABEL_ENABLED);
    }
    
    public boolean isVerticalAxisGridEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_VERTICAL_AXIS_GRID_ENABLED);
    }
    
    public String getVerticalAxisLabelRangeDValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_D_VALUES);
        return UIDLUtil.getJSArray(values, false);
    }
    
    public String getVerticalAxisLabelRangeSValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_S_VALUES);
        return UIDLUtil.getJSArray(values, true);
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
    
    public double getPaddingLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_PADDING_LEFT);
    }
    public double getPaddingRight() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_PADDING_RIGHT);
    }
    public double getPaddingTop() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_PADDING_TOP);
    }
    public double getPaddingBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_PADDING_BOTTOM);
    }
    
    public String getInterpolationMode() {
        return this.currentUIDL.getStringVariable(UIDL_OPTIONS_INTERPOLATION_MODE);
    }
    
    public int getLineWidth() {
        return this.currentUIDL.getIntVariable(UIDL_OPTIONS_LINE_WIDTH);
    }
    public boolean isLegendEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_LEGEND_ENABLED);
    }
    public double getLegendAreaWidth() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEGEND_AREA_WIDTH);
    }
    public String getSerieNames() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_NAMES);
        return UIDLUtil.getJSArray(values, true);
    }
}
