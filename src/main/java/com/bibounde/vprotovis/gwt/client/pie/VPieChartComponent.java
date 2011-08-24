package com.bibounde.vprotovis.gwt.client.pie;

import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.Tooltip;
import com.bibounde.vprotovis.gwt.client.Tooltip.ArrowStyle;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VPieChartComponent extends Widget implements Paintable {
    
    public static final String UIDL_DIV_ID = "vprotovis.div.id";
    public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
    public static final String UIDL_DATA_SERIES_SUM = "vprotovis.data.series.sum";
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
    public static final String UIDL_OPTIONS_TOOLTIP_ENABLED = "vprotovis.options.tooltip.enabled.";
    public static final String UIDL_OPTIONS_LABEL_ENABLED = "vprotovis.options.label.enabled";
    public static final String UIDL_OPTIONS_LABEL_COLOR = "vprotovis.options.label.color";
    
    
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-piechart";
    
    private Logger LOGGER = Logger.getLogger(VPieChartComponent.class.getName());
    
    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;
    
    private UIDL currentUIDL;
    private Tooltip currentTooltip;
    private int currentTooltipIndex = -1;

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
            label.textAngle(0);
            coordLabel.text(function() {
                tooltipLeft.push(this.left());
                tooltipTop.push(this.top());
                return "";
            });
        
            var tooltipValues = eval(this.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::getTooltipValues()());
            wedge.event("mouseover", function() {
                if (vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::isTooltipEnabled(I)(this.index)) {
                    var x = tooltipLeft[this.index];
                    var y = tooltipTop[this.index];
                    vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::showTooltip(IIDDILjava/lang/String;)(x, y, wedgeLeft, wedgeBottom, this.index, tooltipValues[this.index]);
                }
                
                return this;
            });
            wedge.event('mouseout', function() {
                vpiechart.@com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent::hideTooltip()();
                return this;
            });
        }
        
        vis.render();
    }-*/;
    
    public void showTooltip(int x, int y, double wedgeLeft, double wedgeBottom, int tooltipIndex, String tooltipText) {
        if (this.currentTooltipIndex == tooltipIndex) {
            return;
        }
        this.currentTooltipIndex = tooltipIndex;
        
        int arrowOffset = 10;        
        int top = this.getElement().getAbsoluteTop() + y;
        int left = this.getElement().getAbsoluteLeft() + x;
        
        //Center of the pie
        int centerLeft = this.getElement().getAbsoluteLeft() + Double.valueOf(wedgeLeft).intValue();
        int centerTop = this.getElement().getAbsoluteBottom() - Double.valueOf(wedgeLeft).intValue();
        
        if (this.currentTooltip == null) {
            this.currentTooltip = new Tooltip();
            this.currentTooltip.addCloseHandler(new CloseHandler<PopupPanel>() {
                
                public void onClose(CloseEvent<PopupPanel> event) {
                    currentTooltipIndex = -1;
                }
            });
        }
        this.currentTooltip.setText(tooltipText);
        this.currentTooltip.initArrows();
        
        //Tooltip location calculation
        this.currentTooltip.show();
        
        if (left < centerLeft) {
            //West
            this.currentTooltip.setArrowStyle(ArrowStyle.RIGHT);
            if (top < centerTop) {
                //North
                this.currentTooltip.setPopupPosition(left - this.currentTooltip.getOffsetWidth() + 5, top + 5);
            } else {
                //South
                this.currentTooltip.setPopupPosition(left - this.currentTooltip.getOffsetWidth() + 5, top - 5);
            }
   
        } else {
            //East
            this.currentTooltip.setArrowStyle(ArrowStyle.LEFT);
            if (top < centerTop) {
                //North
                this.currentTooltip.setPopupPosition(left -5, top + 5);
            } else {
                //South
                this.currentTooltip.setPopupPosition(left - 5, top - 5);
            }
            
            
        }
    }
    
    public void hideTooltip() {
        if (this.currentTooltip != null) {
            this.currentTooltip.hide();
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
        StringBuilder ret = new StringBuilder("[");
        
        String[] highlighted = this.currentUIDL.getStringArrayVariable(UIDL_DATA_SERIES_HIGHLIGHTED);
        for (int i = 0; i < highlighted.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append(highlighted[i]);
        }
        ret.append("]");
        return ret.toString();
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
        
        StringBuilder ret = new StringBuilder("[");

        for (int i = 0; i < labelValues.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append("'").append(labelValues[i]).append("'");
        }
        ret.append("]");
        return ret.toString();
    }
    
    public boolean isTooltipsEnabled() {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIPS_ENABLED);
    }
    
    public boolean isTooltipEnabled(int index) {
        return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIP_ENABLED + index);
    }
    
    public String getTooltipValues() {
        String[] tooltipValues = this.currentUIDL.getStringArrayVariable(UIDL_DATA_TOOLTIP_VALUES);
        
        StringBuilder ret = new StringBuilder("[");

        for (int i = 0; i < tooltipValues.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append("'").append(tooltipValues[i]).append("'");
        }
        ret.append("]");
        return ret.toString();
    }
}
