package com.bibounde.vprotovis.gwt.client.spider;

import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.Tooltip;
import com.bibounde.vprotovis.gwt.client.UIDLUtil;
import com.bibounde.vprotovis.gwt.client.VAbstractChartComponent;

public class VSpiderChartComponent extends VAbstractChartComponent {

    public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
    public static final String UIDL_DATA_SERIES_NAMES = "vprotovis.data.series.names";
    public static final String UIDL_DATA_SERIE_VALUE = "vprotovis.data.serie.value.";
    public static final String UIDL_DATA_AXIS_VALUES = "vprotovis.data.axis.values";
    public static final String UIDL_DATA_AXIS_COUNT = "vprotovis.data.axis.count";
    public static final String UIDL_DATA_AXIS_LABEL_VALUES = "vprotovis.data.axis.label.values";
    public static final String UIDL_DATA_AXIS_LABEL_RANGE = "vprotovis.data.axis.label.range";
    public static final String UIDL_DATA_MAX_VALUE = "vprotovis.data.max.value";
    public static final String UIDL_OPTIONS_BOTTOM = "vprotovis.options.bottom";
    public static final String UIDL_OPTIONS_LEFT = "vprotovis.options.left";
    public static final String UIDL_OPTIONS_RULE_WIDTH = "vprotovis.options.rule.width";
    public static final String UIDL_OPTIONS_AREA_ENABLED = "vprotovis.options.area.enabled";
    public static final String UIDL_OPTIONS_AREA_OPACITY = "vprotovis.options.area.opacity";
    public static final String UIDL_OPTIONS_AXIS_ENABLED = "vprotovis.options.axis.enabled";
    public static final String UIDL_OPTIONS_AXIS_CAPTION_ENABLED = "vprotovis.options.axis.caption.enabled";
    public static final String UIDL_OPTIONS_AXIS_LABEL_ENABLED = "vprotovis.options.axis.label.enabled";
    public static final String UIDL_OPTIONS_AXIS_GRID_ENABLED = "vprotovis.options.axis.grid.enabled";
    public static final String UIDL_OPTIONS_AXIS_STEP = "vprotovis.options.axis.step";
    public static final String UIDL_OPTIONS_AXIS_OFFSET = "vprotovis.options.axis.offset";
    public static final String UIDL_OPTIONS_LINE_WIDTH = "vprotovis.options.line.width";
    
    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-spiderchart";

    private Logger LOGGER = Logger.getLogger(VSpiderChartComponent.class.getName());

    private Tooltip currentTooltip;
    
    /**
     * @see com.bibounde.vprotovis.gwt.client.VAbstractChartComponent#getClassName()
     */
    @Override
    public String getClassName() {
        return CLASSNAME;
    }
    
    public native void render()/*-{
    
        var vspiderchart = this;
    
        function theta(angle) {
            return  Math.PI * ((angle + 90)/180);
        }
    
        function createRule(angle, labelText) {
            var axis = vis.add($wnd.pv.Line).data($wnd.pv.range(2)); 
            axis.left(function() {
                return centerLeft + (this.index * Math.cos(theta(angle)) * ruleWidth);
            });
            axis.bottom(function() {
                
                return centerBottom + (this.index * Math.sin(theta(angle)) * ruleWidth);
            });
            axis.lineWidth(1);
            axis.strokeStyle(axisColor);
            
            if (vspiderchart.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isAxisCaptionEnabled()()) {
                var label = axis.anchor("center").add($wnd.pv.Label);
                label.visible(function() {
                    return this.index > 0;
                });
                label.text(labelText);
                label.textMargin(2);
            
                if (angle > 0 && angle < 180) {
                    label.textAlign("right");
                } else if (angle > 180) {
                    label.textAlign("left");
                } else {
                    label.textAlign("center");
                }
            
                if ((angle >= 0 && angle <= 45) || (angle >= 315)) {
                label.textBaseline("bottom");
                } else if ((angle > 45 && angle <= 135) || (angle >= 225 && angle < 315)) {
                    label.textBaseline("middle");
                } else {
                    label.textBaseline("top");
                }
            }
        }
        
        function createGrid(count, step) {
            var axis = vis.add($wnd.pv.Line).data($wnd.pv.range(count)); 
            axis.left(function() {
                return centerLeft + Math.cos(theta(this.index * angle)) * step;
            });
            axis.bottom(function() {
                return centerBottom + Math.sin(theta(this.index * angle)) * step;
            });
            axis.lineWidth(1);
            axis.strokeStyle(axisColor);
        
        }
        
        function createTicks(range, labels, step) {
            var rule = vis.add($wnd.pv.Rule).data(range);
            rule.width(5);
            rule.left(centerLeft - 2);
            rule.bottom(function(d) {
                return centerBottom + (step * d);
            });
            rule.strokeStyle(axisColor);
            var label = rule.anchor("center").add($wnd.pv.Label);
            label.text(function() {
                return labels[this.index];
            });
            label.textAlign("right");
            label.textMargin(5);
            label.textStyle(axisColor);
        }
        
        function createDataLine(data, color) {
            var line = vis.add($wnd.pv.Line).data(data);
            line.left( function(d) {
                return centerLeft + Math.cos(theta(this.index * angle)) * (dataStep * d);
            });
            line.bottom(function(d) {
                return centerBottom + Math.sin(theta(this.index * angle)) * (dataStep * d);
            });
            line.strokeStyle(color);
            if (vspiderchart.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isAreaEnabled()()) {
                line.fillStyle(color.rgb().alpha(vspiderchart.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAreaOpacity()()));
            }
            line.lineWidth(vspiderchart.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getLineWidth()());
        }
        
        function createLegend() {
            var legengTop = marginTop + (chartHeight - marginBottom - marginTop - (serieNames.length * 18)) / 2;
            //Use bar instead of DOT because msie-shim does not support it
            var legend = vis.add($wnd.pv.Bar).data(serieNames);
            legend.top(function(){
                return legengTop + (this.index * 18);
            });
            legend.width(11).height(11).left(chartWidth - marginRight - legendAreaWidth + legendInsetLeft);
            legend.fillStyle(colors.by($wnd.pv.index));
            legend.anchor("left").add($wnd.pv.Label).textBaseline("middle").textMargin(16).textStyle(legendColor);
        }
    
        var data = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getData()());
        var colors = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getColors()());
        var axisColor = $wnd.pv.color("#969696");
        var legendColor = $wnd.pv.color("#464646");
        var columns = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAxisNames()());
        var serieNames = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getSerieNames()());
        var ruleWidth = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getRuleWidth()();
        var centerBottom = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getCenterBottom()();
        var centerLeft = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getCenterLeft()();
        var angle = 360 / columns.length;
        var maxValue = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getMaxValue()();
        var axisOffset = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAxisOffset()();
        var dataStep = (ruleWidth - axisOffset)/maxValue;
        
        var marginLeft = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getMarginLeft()();
        var marginRight = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getMarginRight()();
        var marginBottom = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getMarginBottom()();
        var marginTop = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getMarginTop()();
        
        var chartWidth = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getChartWidth()();
        var chartHeight = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getChartHeight()();
        
        var vis = new $wnd.pv.Panel().canvas(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getDivId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
        
        if (this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isAxisEnabled()()) {
            //Rule creation
            for (i=0; i< columns.length; i++) {
                createRule(i * angle, columns[i]);
            }
            
            var axisStep = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAxisStep()();
            var gridStep = (ruleWidth - axisOffset) / (axisStep * maxValue);
            
            if (this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isAxisGridEnabled()()) {
                //Create grid
                for (i=1; i<= maxValue; i++) {
                    createGrid(columns.length + 1, i * gridStep);
                }
            }
            
            if (this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isAxisLabelEnabled()()) {
                //Create ticks
                var range = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAxisRange()());
                var labels = eval(this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getAxisLabels()());
                createTicks(range, labels, gridStep);
            }
        }
        
        //Display data
        for (i=0; i<data.length; i++) {
            createDataLine(data[i], colors.range()[i]);
        }
        
        //Legend management
        if (this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::isLegendEnabled()()) {
            var legendAreaWidth = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getLegendAreaWidth()();
            var legendInsetLeft = this.@com.bibounde.vprotovis.gwt.client.spider.VSpiderChartComponent::getLegendInsetLeft()();
            createLegend();
        }
        
        vis.render();
    }-*/;
    
    
    public String getData() {
        int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_SERIES_COUNT);
        
        StringBuilder ret = new StringBuilder("[");
        
        for (int i = 0; i < serieCount; i++) {
            if (i > 0) {
                ret.append(",");
            }
            String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIE_VALUE + i);
            ret.append(UIDLUtil.getJSArray(values, false));
        }
        
        ret.append("]");
        return ret.toString();
    }
    
    public String getAxisNames() {
        return UIDLUtil.getJSArray(this.currentUIDL.getStringArrayVariable(UIDL_DATA_AXIS_VALUES), true);
    }
    
    public String getSerieNames() {
        return UIDLUtil.getJSArray(this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_NAMES), true);
    }
    
    public double getRuleWidth() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_RULE_WIDTH);
    }
    
    public double getCenterBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BOTTOM);
    }
    
    public double getCenterLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEFT);
    }
    
    public boolean isAxisEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_AXIS_ENABLED);
    }
    
    public double getAxisOffset() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AXIS_OFFSET);
    }
    
    public double getAxisStep() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AXIS_STEP);
    }
    
    public double getMaxValue() {
        return this.currentUIDL.getDoubleVariable(UIDL_DATA_MAX_VALUE);
    }
    
    public boolean isAxisGridEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_AXIS_GRID_ENABLED);
    }
    
    public boolean isAxisLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_AXIS_LABEL_ENABLED);
    }
    
    public boolean isAxisCaptionEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_AXIS_CAPTION_ENABLED);
    }
    
    public int getLineWidth() {
        return this.currentUIDL.getIntVariable(UIDL_OPTIONS_LINE_WIDTH);
    }
    
    public boolean isAreaEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_AREA_ENABLED);
    }
    public double getAreaOpacity() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AREA_OPACITY);
    }
    public String getAxisLabels() {
        return UIDLUtil.getJSArray(this.currentUIDL.getStringArrayVariable(UIDL_DATA_AXIS_LABEL_VALUES), true);
    }
    
    public String getAxisRange() {
        return UIDLUtil.getJSArray(this.currentUIDL.getStringArrayVariable(UIDL_DATA_AXIS_LABEL_RANGE), false);
    }
}
