package com.bibounde.vprotovis.gwt.client.pie;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.Tooltip;
import com.bibounde.vprotovis.gwt.client.Tooltip.ArrowStyle;
import com.bibounde.vprotovis.gwt.client.UIDLUtil;
import com.bibounde.vprotovis.gwt.client.UIRectangle;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VPieChartComponent extends Widget implements Paintable {
    
    public static final String UIDL_DIV_ID = "vprotovis.div.id";
    public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
    public static final String UIDL_DATA_SERIES_SUM = "vprotovis.data.series.sum";
    public static final String UIDL_DATA_SERIES_NAMES = "vprotovis.data.series.names";
    public static final String UIDL_DATA_SERIES_HIGHLIGHTED = "vprotovis.data.series.highlithed";
    public static final String UIDL_DATA_LABEL_VALUES = "vprotovis.data.label.values";
    public static final String UIDL_DATA_SERIE_VALUE = "vprotovis.data.serie.value.";
    public static final String UIDL_DATA_TOOLTIP_VALUES = "vprotovis.data.tooltip.values.";
    public static final String UIDL_OPTIONS_WIDTH = "vprotovis.options.width";
    public static final String UIDL_OPTIONS_HEIGHT = "vprotovis.options.height";
    public static final String UIDL_OPTIONS_BOTTOM = "vprotovis.options.bottom";
    public static final String UIDL_OPTIONS_LEFT = "vprotovis.options.left";
    public static final String UIDL_OPTIONS_RADIUS = "vprotovis.options.radius";
    public static final String UIDL_OPTIONS_HIGHLIGHT_OFFSET = "vprotovis.options.highlight.offset";
    public static final String UIDL_OPTIONS_MARGIN_LEFT = "vprotovis.options.margin.left";
    public static final String UIDL_OPTIONS_MARGIN_RIGHT = "vprotovis.options.margin.right";
    public static final String UIDL_OPTIONS_MARGIN_TOP= "vprotovis.options.margin.top";
    public static final String UIDL_OPTIONS_MARGIN_BOTTOM = "vprotovis.options.margin.bottom";
    public static final String UIDL_OPTIONS_PADDING_LEFT = "vprotovis.options.padding.left";
    public static final String UIDL_OPTIONS_COLORS = "vprotovis.options.colors";
    public static final String UIDL_OPTIONS_LEGEND_ENABLED = "vprotovis.options.legend.enabled";
    public static final String UIDL_OPTIONS_LEGEND_AREA_WIDTH = "vprotovis.options.legend.area.width";
    public static final String UIDL_OPTIONS_TOOLTIPS_ENABLED = "vprotovis.options.tooltips.enabled";
    public static final String UIDL_OPTIONS_TOOLTIPS_PERMANENT = "vprotovis.options.tooltips.permanent";
    public static final String UIDL_OPTIONS_TOOLTIP_ENABLED = "vprotovis.options.tooltip.enabled.";
    public static final String UIDL_OPTIONS_LABEL_ENABLED = "vprotovis.options.label.enabled";
    public static final String UIDL_OPTIONS_LABEL_COLOR = "vprotovis.options.label.color";
    public static final String UIDL_OPTIONS_LINE_WIDTH = "vprotovis.options.line.width";
    public static final String UIDL_OPTIONS_LINE_COLOR = "vprotovis.options.line.color";
    
    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-piechart";
    
    private Logger LOGGER = Logger.getLogger(VPieChartComponent.class.getName());
    
    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;
    
    private UIDL currentUIDL;
    private Map<Integer, Tooltip> tooltipMap = new HashMap<Integer, Tooltip>();

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public VPieChartComponent() {
        DivElement canvas = Document.get().createDivElement();
        setElement(canvas);
        setStyleName(CLASSNAME);
    }
    
    /**
     * Called whenever an update is received from the server 
     */
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
        
        execChart();
    }
    
    private native void execChart() /*-{
        var vpiechart = this;

        var colors = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getColors()());
        var legendColor = $wnd.pv.color("#464646");

        var data = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getData()());
        var sum = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getSum()();
        var highlighted = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getHighlighted()());

        var chartWidth = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getChartWidth()();
        var chartHeight = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getChartHeight()();
        
        var vis = new $wnd.pv.Panel().canvas(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getDivId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
        
        var marginLeft = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getMarginLeft()();
        var marginRight = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getMarginRight()();
        var marginBottom = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getMarginBottom()();
        var marginTop = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getMarginTop()();
        var legendAreaWidth = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getLegendAreaWidth()();
        
        var wedgeBottom = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getWedgeBottom()();
        var wedgeLeft = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getWedgeLeft()();
        var wedgeRadius = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getWedgeRadius()();
        var wedgeHighlightOffset = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getWedgeHighlightOffset()();
        
        var wedge = vis.add($wnd.pv.Wedge).data(data);
        wedge.outerRadius(wedgeRadius);
        wedge.angle(function(d){return d / sum * 2 * Math.PI});
        wedge.left(function() {
            return wedgeLeft + Math.cos(this.startAngle() + this.angle() / 2) * ((highlighted[this.index]) ? wedgeHighlightOffset : 0);
        });
        wedge.bottom(function() {
            return wedgeBottom - Math.sin(this.startAngle() + this.angle() / 2) * ((highlighted[this.index]) ? wedgeHighlightOffset : 0);
        });
        wedge.fillStyle(colors.by($wnd.pv.index));
        wedge.strokeStyle(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getLineColor()());
        wedge.lineWidth(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getLineWidth()());
        
        
        //Label management
        if (this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isLabelEnabled()()) {
            var labelColor = this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getLabelColor()();
            var labelValues = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getLabelValues()());
            var label = wedge.anchor("center").add($wnd.pv.Label);
            label.textAngle(0);
            label.text(function() {
                return labelValues[this.index];
            });
            label.textStyle(labelColor);
        }
        
        //Tooltip management
        if (this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isTooltipsEnabled()()) {
        
            //Add anchor in order to obtain coordinates
            var tooltipLeft = new Array();
            var tooltipTop = new Array();
            var coordLabel = wedge.anchor("outer").add($wnd.pv.Label);
            coordLabel.text(function() {
                tooltipLeft.push(this.left());
                tooltipTop.push(this.top());
            
                if (vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isTooltipsPermanent()()) {
                    //Display permanent tooltip
                    vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::showTooltip(III)(this.left(), this.top(), this.index);
                }
                return "";
            });
            
            if (!this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isTooltipsPermanent()()) {
                //Register dynamic tooltips
                wedge.event("mouseover", function() {
                    if (vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isTooltipEnabled(I)(this.index)) {
                        var x = tooltipLeft[this.index];
                        var y = tooltipTop[this.index];
                        vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::showTooltip(III)(x, y, this.index);
                    }
                
                    return this;
                });
                wedge.event('mouseout', function() {
                    vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::hideTooltip(I)(this.index);
                    return this;
                });
            }
        }
        
        //Legend management
        if (this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isLegendEnabled()()) {
            var serieNames = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getSerieNames()());
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
    
    public void showTooltip(int x, int y, final int tooltipIndex) {
        if (this.tooltipMap.containsKey(tooltipIndex)) {
            return;
        }
        
        //Center of the pie (Do not use bottom because 'this' could be not displayed)
        double wedgeLeft = this.getWedgeLeft();
        double wedgeBottom = this.getWedgeBottom();
        double chartHeight = this.getChartHeight();
        double centerLeft = this.getElement().getAbsoluteLeft() + wedgeLeft;
        double centerTop = this.getElement().getAbsoluteTop() + chartHeight - wedgeBottom;
        
        Tooltip tooltip = this.tooltipMap.get(tooltipIndex); 
        
        if (tooltip == null) {
            tooltip = new Tooltip();
            tooltip.addCloseHandler(new CloseHandler<PopupPanel>() {
                
                public void onClose(CloseEvent<PopupPanel> event) {
                    tooltipMap.remove(tooltipIndex);
                }
            });
            this.tooltipMap.put(tooltipIndex, tooltip);
        }
        tooltip.setText(this.getTooltipValues()[tooltipIndex]);
        tooltip.initArrows();
        
        //Tooltip location calculation
        tooltip.show();
        
        int top = this.getElement().getAbsoluteTop() + y;
        int left = this.getElement().getAbsoluteLeft() + x;
        int tooltipArrowOffset = 10;
        int tooltipMarginOffset = 5;
        double wedgeRadius = this.getWedgeRadius();
        double wedgeHighlightOffset = this.getWedgeHighlightOffset();
        double locationWidth = ((wedgeRadius + wedgeHighlightOffset) * 2) / 3;
        double locationHeight = ((wedgeRadius + wedgeHighlightOffset) * 2) / 3;
        
        UIRectangle nw = new UIRectangle(centerLeft - wedgeRadius - wedgeHighlightOffset, centerTop - wedgeRadius - wedgeHighlightOffset, locationWidth, locationHeight);
        UIRectangle n = new UIRectangle(nw.left + locationWidth, nw.top, locationWidth, locationHeight);
        UIRectangle ne = new UIRectangle(n.left + locationWidth, n.top, locationWidth, locationHeight);
        
        UIRectangle w = new UIRectangle(nw.left, nw.top + locationHeight, locationWidth, locationHeight);
        UIRectangle c = new UIRectangle(n.left, w.top, locationWidth, locationHeight);
        UIRectangle e = new UIRectangle(ne.left, w.top,locationWidth, locationHeight);
        
        UIRectangle sw = new UIRectangle(w.left, w.top + locationHeight, locationWidth, locationHeight);
        UIRectangle s = new UIRectangle(c.left, sw.top, locationWidth, locationHeight);
        UIRectangle se = new UIRectangle(e.left, sw.top, locationWidth, locationHeight);
        
        if (nw.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.BOTTOM);
            tooltip.setPopupPosition(left - tooltipArrowOffset + 5, top - tooltip.getOffsetHeight() + 5);
        } else if (n.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.BOTTOM);
            tooltip.setPopupPosition(left - tooltipArrowOffset, top - tooltip.getOffsetHeight() + 5);
        } else if (ne.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.BOTTOM);
            tooltip.setPopupPosition(left - tooltipArrowOffset - 5, top - tooltip.getOffsetHeight() + 5);
        } else if (w.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.RIGHT);
            tooltip.setPopupPosition(left - tooltip.getOffsetWidth() + 5, top - tooltipMarginOffset);
        } else if (e.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.LEFT);
            tooltip.setPopupPosition(left - 5, top - tooltipMarginOffset);
        } else if (sw.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.TOP);
            tooltip.setPopupPosition(left - tooltipArrowOffset + 5, top - tooltipArrowOffset - 5);
        } else if (s.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.TOP);
            tooltip.setPopupPosition(left - tooltipArrowOffset, top - tooltipArrowOffset - 5);
        } else if (se.contains(left, top)) {
            tooltip.setArrowStyle(ArrowStyle.TOP);
            tooltip.setPopupPosition(left - tooltipArrowOffset - 5, top - tooltipArrowOffset - 5);
        } else {
            tooltip.setArrowStyle(ArrowStyle.RIGHT);
            tooltip.setPopupPosition(left - tooltip.getOffsetWidth(), top - tooltipMarginOffset);  
        }
    }
    
    public void hideTooltip(int tooltipIndex) {
        if (this.tooltipMap.containsKey(tooltipIndex)) {
            this.tooltipMap.get(tooltipIndex).hide();
        }
    }
    
    
    public String getDivId() {
        return this.currentUIDL.getStringVariable(UIDL_DIV_ID);
    }
    
    public String getData() {
        StringBuilder ret = new StringBuilder("[");
        
        int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_SERIES_COUNT);
        for (int i = 0; i < serieCount; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append(this.currentUIDL.getStringVariable(UIDL_DATA_SERIE_VALUE + i));
        }
        
        ret.append("]");
        return ret.toString();
    }
    
    public String getHighlighted() {
        String[] highlighted = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_HIGHLIGHTED);
        return UIDLUtil.getJSArray(highlighted, false);
    }
    
    public double getSum() {
        return this.currentUIDL.getDoubleVariable(UIDL_DATA_SERIES_SUM);
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
    
    public double getWedgeBottom() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BOTTOM);
    }
    
    public double getWedgeLeft() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEFT);
    }
    
    public double getWedgeHighlightOffset() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_HIGHLIGHT_OFFSET);
    }
    
    public double getWedgeRadius() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_RADIUS);
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
    
    public boolean isLegendEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_LEGEND_ENABLED);
    }
    
    public double getLegendAreaWidth() {
        return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEGEND_AREA_WIDTH);
    }
    
    public boolean isLabelEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_LABEL_ENABLED);
    }
    
    public String getLabelColor() {
        return this.currentUIDL.getStringVariable(UIDL_OPTIONS_LABEL_COLOR);
    }
    
    public String getLabelValues() {
        String[] labelValues = this.currentUIDL.getStringArrayVariable(UIDL_DATA_LABEL_VALUES);
        return UIDLUtil.getJSArray(labelValues, true);
    }
    
    public boolean isTooltipsEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIPS_ENABLED);
    }
    
    public boolean isTooltipsPermanent() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIPS_PERMANENT);
    }
    
    public boolean isTooltipEnabled(int index) {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIP_ENABLED + index);
    }
    
    public String[] getTooltipValues() {
        return this.currentUIDL.getStringArrayVariable(UIDL_DATA_TOOLTIP_VALUES);
    }
    
    public String getSerieNames() {
        String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_NAMES);
        return UIDLUtil.getJSArray(values, true);
    }
    
    public int getLineWidth() {
        return this.currentUIDL.getIntVariable(UIDL_OPTIONS_LINE_WIDTH);
    }
    
    public String getLineColor() {
        return this.currentUIDL.getStringVariable(UIDL_OPTIONS_LINE_COLOR);
    }
}
