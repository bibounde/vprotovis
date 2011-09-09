package com.bibounde.vprotovis.gwt.client.area;

import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.Tooltip;
import com.bibounde.vprotovis.gwt.client.TooltipComposite.ArrowStyle;
import com.bibounde.vprotovis.gwt.client.TooltipOptions;
import com.bibounde.vprotovis.gwt.client.UIDLUtil;
import com.bibounde.vprotovis.gwt.client.VAbstractChartComponent;

public class VAreaChartComponent extends VAbstractChartComponent {

    public static final String UIDL_DATA_SERIES_NAMES = "vprotovis.data.series.names";
    public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
    public static final String UIDL_DATA_SERIE_VALUES = "vprotovis.data.serie.values.";
    public static final String UIDL_DATA_SERIE_TOOLTIP_VALUES = "vprotovis.data.serie.tooltip.values.";
    public static final String UIDL_OPTIONS_BOTTOM = "vprotovis.options.bottom";
    public static final String UIDL_OPTIONS_LEFT = "vprotovis.options.left";
    public static final String UIDL_OPTIONS_AREA_BOTTOM = "vprotovis.options.line.bottom";
    public static final String UIDL_OPTIONS_AREA_LEFT = "vprotovis.options.line.left";
    public static final String UIDL_OPTIONS_PADDING_LEFT = "vprotovis.options.padding.left";
    public static final String UIDL_OPTIONS_PADDING_RIGHT = "vprotovis.options.padding.right";
    public static final String UIDL_OPTIONS_PADDING_TOP= "vprotovis.options.padding.top";
    public static final String UIDL_OPTIONS_PADDING_BOTTOM = "vprotovis.options.padding.bottom";
    public static final String UIDL_OPTIONS_TOOLTIP_ENABLED = "vprotovis.options.tooltip.enabled";
    public static final String UIDL_OPTIONS_X_AXIS_ENABLED = "vprotovis.options.x.axis.enabled";
    public static final String UIDL_OPTIONS_X_AXIS_LABEL_ZERO_ENABLED = "vprotovis.options.x.axis.label.zero.enabled";
    public static final String UIDL_OPTIONS_X_AXIS_LABEL_ENABLED = "vprotovis.options.x.axis.label.enabled";
    public static final String UIDL_OPTIONS_X_AXIS_LABEL_RANGE_D_VALUES = "vprotovis.options.x.axis.label.range.d.values";
    public static final String UIDL_OPTIONS_X_AXIS_LABEL_RANGE_S_VALUES = "vprotovis.options.x.axis.label.range.s.values";
    public static final String UIDL_OPTIONS_X_AXIS_GRID_ENABLED = "vprotovis.options.x.axis.grid.enabled";
    public static final String UIDL_OPTIONS_Y_AXIS_LABEL_ZERO_ENABLED = "vprotovis.options.y.axis.label.zero.enabled";
    public static final String UIDL_OPTIONS_Y_AXIS_ENABLED = "vprotovis.options.y.axis.enabled";
    public static final String UIDL_OPTIONS_Y_AXIS_LABEL_ENABLED = "vprotovis.options.y.axis.label.enabled";
    public static final String UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_D_VALUES = "vprotovis.options.y.axis.label.range.d.values";
    public static final String UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_S_VALUES = "vprotovis.options.y.axis.label.range.s.values";
    public static final String UIDL_OPTIONS_Y_AXIS_GRID_ENABLED = "vprotovis.options.y.axis.grid.enabled";
    public static final String UIDL_OPTIONS_INTERPOLATION_MODE = "vprotovis.options.interpolation.mode";
    public static final String UIDL_OPTIONS_LINE_WIDTH = "vprotovis.options.line.width";
    public static final String UIDL_OPTIONS_AREA_OPACITY = "vprotovis.options.area.opacity";

    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-areachart";

    private Logger LOGGER = Logger.getLogger(VAreaChartComponent.class.getName());

    private Tooltip currentTooltip;
    
    private int[] currentTooltipDataIndex;
    
    /**
     * @see com.bibounde.vprotovis.gwt.client.VAbstractChartComponent#getClassName()
     */
    @Override
    public String getClassName() {
        return CLASSNAME;
    }

    public native void render() /*-{
        var vareachart = this;
        
        var colors = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getColors()());
        var axisColor = $wnd.pv.color("#969696");
        var legendColor = $wnd.pv.color("#464646");
        var gridColor = $wnd.pv.color("#E4E4E4");
        
        var data = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getData()());
        var serieNames = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getSerieNames()());

        var chartWidth = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getChartWidth()();
        var chartHeight = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getChartHeight()();
        
        var marginLeft = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getMarginLeft()();
        var marginRight = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getMarginRight()();
        var marginBottom = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getMarginBottom()();
        var marginTop = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getMarginTop()();
        var paddingLeft = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPaddingLeft()();
        var paddingRight = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPaddingRight()();
        var paddingBottom = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPaddingBottom()();
        var paddingTop = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPaddingTop()();
        
        var legendAreaWidth = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getLegendAreaWidth()();
        var legendInsetLeft = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getLegendInsetLeft()();
        
        var maxYTick = chartHeight - marginTop;
        var minYTick = marginBottom;
        var maxXTick = chartWidth - marginRight - legendAreaWidth - legendInsetLeft;
        var minXTick = marginLeft;
        
        var xRange = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getXAxisLabelRangeDValues()());
        var xRangeText = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getXAxisLabelRangeSValues()());
        
        var yRange = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getYAxisLabelRangeDValues()());
        var yRangeText = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getYAxisLabelRangeSValues()());
        
        var panelBottom = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPanelBottom()();
        var panelLeft = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getPanelLeft()();
        
        var vis = new $wnd.pv.Panel().canvas(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getDivId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
        
        //Grid management
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isXAxisGridEnabled()()) {
            var grid = vis.add($wnd.pv.Rule).data(xRange);
            grid.visible(function(d) {
                var ret = (d * areaLeft) + panelLeft;
                return ret <= maxXTick && ret >= minXTick;
            });
            grid.left(function(d) {
               return (d * areaLeft) + panelLeft;
            });
            grid.bottom(0 + marginBottom);
            grid.height(chartHeight - marginBottom - marginTop);
            grid.strokeStyle(gridColor);
        }
        
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isYAxisGridEnabled()()) {
            var grid = vis.add($wnd.pv.Rule).data(yRange);
            grid.visible(function(d) {
                var ret = (d * areaBottom) + panelBottom;
                return ret <= maxYTick && ret >= minYTick;
            });
            grid.bottom(function(d) {
                return (d * areaBottom) + panelBottom;
            });
            grid.left(0 + marginLeft);
            grid.width(chartWidth - marginLeft - marginRight - legendAreaWidth - legendInsetLeft);
            grid.strokeStyle(gridColor);
        }
        
        var areaBottom = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getAreaBottom()();
        var areaLeft = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getAreaLeft()();
        var areaWidth = this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getLineWidth()();
        
        //Initialize coords. Usefull for tooltips and point behavior fix
        var leftValues = new Array();
        var bottomValues = new Array();
        
        for(i=0;i<data.length;i++) {
            var areaData = data[i];
            leftValues.push(new Array());
            bottomValues.push(new Array());
            
            for(j=0;j<areaData.length;j++) {
                var d = areaData[j];
                var left = d.x * areaLeft;
                var bottom = d.y * areaBottom;
                leftValues[i].push(left);
                bottomValues[i].push(bottom);
                
                this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::putBehaviorPointInfo(IIII)(chartHeight - panelBottom - bottom, panelLeft + left, i, j);
            }
        }
        
        var panel = vis.add($wnd.pv.Panel).data(data).bottom(panelBottom).left(panelLeft);
         
        var area = panel.add($wnd.pv.Area).data(function(d) {return d;});
        
        area.left(function() {
            return leftValues[this.parent.index][this.index];
        });
        area.bottom(0);
        area.height(function() {
            return bottomValues[this.parent.index][this.index];
        });
        area.interpolate(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getInterpolationMode()());
        area.fillStyle(function() {return colors.range()[this.parent.index].rgb().alpha(vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getAreaOpacity()());});
        
        var line = area.anchor("top").add($wnd.pv.Line);
        line.lineWidth(areaWidth);
        line.strokeStyle(function() {return colors.range()[this.parent.index];});
        
        //Horizontal axis management
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isXAxisEnabled()()) {
        
            var rule = vis.add($wnd.pv.Rule);
            
            rule.bottom(panelBottom).width(chartWidth - marginLeft - marginRight - legendAreaWidth - legendInsetLeft).left(0 + marginLeft);
            rule.strokeStyle(axisColor);
        
            if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isXAxisLabelEnabled()()) {
        
                var tick = vis.add($wnd.pv.Rule).data(xRange);
                tick.visible(function(d) {
                    if (d == 0 && !vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isXAxisLabelZeroEnabled()()) {
                        return false;
                    }
                    var ret = (d * areaLeft) + panelLeft;
                    return (ret <= maxXTick && ret >= minXTick);
                });
                tick.left(function(d) {
                    return (d * areaLeft) + panelLeft;
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
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isYAxisEnabled()()) {
            var rule = vis.add($wnd.pv.Rule);
            rule.bottom(0 + marginBottom).left(panelLeft).height(chartHeight - marginBottom - marginTop);
            rule.strokeStyle(axisColor);
            
            if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isYAxisLabelEnabled()()) {
        
                var tick = vis.add($wnd.pv.Rule).data(yRange);
                tick.visible(function(d) {
                    if (d == 0 && !vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isYAxisLabelZeroEnabled()()) {
                        return false;
                    }
                    var ret = (d * areaBottom) + panelBottom;
                    return (ret <= maxYTick && ret >= minYTick);
                });
                tick.bottom(function(d) {
                    return (d * areaBottom) + panelBottom;
                });
                tick.left(panelLeft - 3);
                tick.width(3);
                tick.strokeStyle(axisColor);
                tick.anchor("left").add($wnd.pv.Label).text(function() {
                    return yRangeText[this.index];
                }).textStyle(axisColor);
            }
        }
        
        //Tooltip management
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isTooltipEnabled()()) {
            vis.def("valueIndex", -1).def("serieIndex", -1);
            vis.events("all");
        
            var tooltips = eval(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getTooltips()());
            vis.event("mousemove", function(){
                var c = eval(vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getClosestBehaviorPointInfo()());
                vis.serieIndex(c[0]);
                vis.valueIndex(c[1]);
                
                if (c[0] >= 0) {
                    var left = panelLeft + leftValues[vis.serieIndex()][vis.valueIndex()];
                    var top = chartHeight - panelBottom - bottomValues[vis.serieIndex()][vis.valueIndex()];
                    vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::showTooltip(IIIILjava/lang/String;)(left, top, vis.serieIndex(), vis.valueIndex(), tooltips[vis.serieIndex()][vis.valueIndex()]);
                } else {
                    vareachart.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::hideTooltip()();
                }
                
                return vis;
            });
            var dot = vis.add($wnd.pv.Dot);
            dot.visible(function() {
                return vis.valueIndex() >= 0;
            });
            dot.left(function() {
                return panelLeft + leftValues[vis.serieIndex()][vis.valueIndex()];
            });
            dot.bottom(function() {
                return panelBottom + bottomValues[vis.serieIndex()][vis.valueIndex()];
            });
            dot.fillStyle(function() {
                return colors.range()[vis.serieIndex()];
            });
            dot.strokeStyle("rgba(255, 255, 255, 0.5)");
            dot.radius(this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::getRadiusWidth()());
        }
        
        
        //Legend management
        if (this.@com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent::isLegendEnabled()()) {
            //Use bar instead of DOT because msie-shim does not support it
            var legend = vis.add($wnd.pv.Bar).data(serieNames);
            legend.top(function(){
                return marginTop + (this.index * 18);
            });
            legend.width(11).height(11).left(chartWidth - marginRight - legendAreaWidth + legendInsetLeft);
            legend.fillStyle(colors.by($wnd.pv.index));
            legend.anchor("left").add($wnd.pv.Label).textBaseline("middle").textMargin(16).textStyle(legendColor);
        }
        vis.render();   
    }-*/;
    
    public void showTooltip(int x, int y, int serieIndex, int valueIndex, String tooltipText) {
        int[] coords = new int[]{serieIndex, valueIndex};
        if (this.currentTooltip != null && this.currentTooltipDataIndex != null) {
            if (coords[0] == this.currentTooltipDataIndex[0] && coords[1] == this.currentTooltipDataIndex[1]) {
                //Already displayed
                return;
            }
        }
        this.currentTooltipDataIndex = coords;
        
        this.hideTooltip();
        this.currentTooltip = new Tooltip();
        
        this.currentTooltip.setText(tooltipText);
        
        //Tooltip location calculation
        this.currentTooltip.show();
        TooltipOptions options = this.getTooltipOptions(this.currentTooltip.getOffsetWidth(), this.currentTooltip.getOffsetHeight(), x, y);
        
        this.currentTooltip.setArrowStyle(options.arrowStyle);
        this.currentTooltip.setPopupPosition(options.left, options.top);
    }
    
    public void hideTooltip() {
        if (this.currentTooltip != null) {
            this.currentTooltip.hide();
            this.currentTooltip = null;
        }
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

    public double getAreaBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AREA_BOTTOM);
    }

    public double getAreaLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AREA_LEFT);
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
    
    public boolean isXAxisEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_X_AXIS_ENABLED);
    }
    
    public boolean isXAxisLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_X_AXIS_LABEL_ENABLED);
    }
    
    public boolean isXAxisLabelZeroEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_X_AXIS_LABEL_ZERO_ENABLED);
    }
    
    public boolean isXAxisGridEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_X_AXIS_GRID_ENABLED);
    }
    
    public String getXAxisLabelRangeDValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_X_AXIS_LABEL_RANGE_D_VALUES);
        return UIDLUtil.getJSArray(values, false);
    }
    
    public String getXAxisLabelRangeSValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_X_AXIS_LABEL_RANGE_S_VALUES);
        return UIDLUtil.getJSArray(values, true);
    }

    public boolean isYAxisEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_Y_AXIS_ENABLED);
    }
    
    public boolean isYAxisLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_Y_AXIS_LABEL_ENABLED);
    }
    
    public boolean isYAxisLabelZeroEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_Y_AXIS_LABEL_ZERO_ENABLED);
    }
    
    public boolean isYAxisGridEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_Y_AXIS_GRID_ENABLED);
    }
    
    public String getYAxisLabelRangeDValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_D_VALUES);
        return UIDLUtil.getJSArray(values, false);
    }
    
    public String getYAxisLabelRangeSValues() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_S_VALUES);
        return UIDLUtil.getJSArray(values, true);
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
    
    public double getAreaOpacity() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_AREA_OPACITY);
    }
    
    public int getRadiusWidth() {
        return this.getLineWidth() + 2;
    }
    
    public String getSerieNames() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_NAMES);
        return UIDLUtil.getJSArray(values, true);
    }
    
    public boolean isTooltipEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIP_ENABLED);
    }
    
    public String getTooltips() {
        StringBuilder ret = new StringBuilder("[");
        
        int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_SERIES_COUNT);
        for (int i = 0; i < serieCount; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIE_TOOLTIP_VALUES + i);
            
            ret.append("[");
            for (int j = 0; j < values.length; j++) {
                if (j > 0) {
                    ret.append(", ");
                }
                ret.append("'").append(values[j]).append("'");
            }
            ret.append("]");
        }
        ret.append("]");
        return ret.toString();
    }
    
    private TooltipOptions getTooltipOptions(int tooltipWidth, int tooltipHeight, int x, int y) {
        
        int left = this.getElement().getAbsoluteLeft() + x;
        int top = this.getElement().getAbsoluteTop() + y;
        
        int tooltipArrowOffset = 10;
        int tooltipMarginOffset = 5;
        
        TooltipOptions ret = new TooltipOptions();
        ret.arrowStyle = ArrowStyle.BOTTOM_LEFT;
        ret.left = left - tooltipArrowOffset;
        ret.top = top - tooltipHeight - tooltipArrowOffset -  this.getRadiusWidth() - 2;
        return ret;
    }
}
