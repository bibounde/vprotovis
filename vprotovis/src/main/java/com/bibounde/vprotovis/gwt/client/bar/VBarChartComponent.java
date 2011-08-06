package com.bibounde.vprotovis.gwt.client.bar;

import java.util.logging.Logger;

import com.bibounde.vprotovis.gwt.client.Tooltip;
import com.bibounde.vprotovis.gwt.client.Tooltip.ArrowStyle;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VBarChartComponent extends Widget implements Paintable {
	
	public static final String UIDL_DIV_ID = "vprotovis.div.id";
	public static final String UIDL_DATA_GROUPS_COUNT = "vprotovis.data.groups.count";
	public static final String UIDL_DATA_GROUP_VALUES = "vprotovis.data.group.values.";
	public static final String UIDL_DATA_GROUP_NAME = "vprotovis.data.group.name.";
	public static final String UIDL_DATA_GROUP_TOOLTIP_VALUES = "vprotovis.data.group.tooltip.values.";
	public static final String UIDL_DATA_SERIE_NAME = "vprotovis.data.serie.name.";
	public static final String UIDL_DATA_SERIES_COUNT = "vprotovis.data.series.count";
	public static final String UIDL_OPTIONS_WIDTH = "vprotovis.options.width";
	public static final String UIDL_OPTIONS_HEIGHT = "vprotovis.options.height";
	public static final String UIDL_OPTIONS_BAR_WIDTH = "vprotovis.options.bar.width";
	public static final String UIDL_OPTIONS_BAR_HEIGHT = "vprotovis.options.bar.height";
	public static final String UIDL_OPTIONS_BAR_INSET = "vprotovis.options.bar.inset";
	public static final String UIDL_OPTIONS_GROUP_WIDTH = "vprotovis.options.group.width";
	public static final String UIDL_OPTIONS_GROUP_INSET = "vprotovis.options.group.inset";
	public static final String UIDL_OPTIONS_GROUP_AXIS_ENABLED = "vprotovis.options.group.axis.enabled";
	public static final String UIDL_OPTIONS_GROUP_AXIS_LABEL_ENABLED = "vprotovis.options.group.axis.label.enabled";
	public static final String UIDL_OPTIONS_VALUE_AXIS_ENABLED = "vprotovis.options.value.axis.enabled";
	public static final String UIDL_OPTIONS_VALUE_AXIS_LABEL_ENABLED = "vprotovis.options.value.axis.label.enabled";
	public static final String UIDL_OPTIONS_VALUE_AXIS_LABEL_PATTERN = "vprotovis.options.value.axis.label.pattern";
	public static final String UIDL_OPTIONS_VALUE_AXIS_LABEL_UNIT = "vprotovis.options.value.axis.label.unit";
	public static final String UIDL_OPTIONS_VALUE_AXIS_LABEL_MAX = "vprotovis.options.value.axis.label.max";
	public static final String UIDL_OPTIONS_VALUE_AXIS_LABEL_STEP = "vprotovis.options.value.axis.label.step";
	public static final String UIDL_OPTIONS_LEGEND_ENABLED = "vprotovis.options.legend.enabled";
	public static final String UIDL_OPTIONS_LEGEND_SIZE = "vprotovis.options.legend.size";
	public static final String UIDL_OPTIONS_VERTICAL_OFFSET = "vprotovis.options.vertical.offset";
	public static final String UIDL_OPTIONS_HORIZONTAL_OFFSET = "vprotovis.options.horizontal.offset";
	public static final String UIDL_OPTIONS_TOOLTIP_ENABLED = "vprotovis.options.tooltip.enabled";
	public static final String UIDL_OPTIONS_COLORS = "vprotovis.options.colors";
	
	
	
    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-vprotovis-barchart";
    
    
    private Logger LOGGER = Logger.getLogger(VBarChartComponent.class.getName());

    /** The client side widget identifier */
    protected String paintableId;

    /** Reference to the server connection object. */
    ApplicationConnection client;
    
    private UIDL currentUIDL;
    
    private NumberFormat labelFormat;
    
    private Tooltip currentTooltip;

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public VBarChartComponent() {
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
        
        this.labelFormat = NumberFormat.getFormat(uidl.getStringVariable(UIDL_OPTIONS_VALUE_AXIS_LABEL_PATTERN));
        
        execChart();
    }
    
    private native void execChart() /*-{
    
        var vbarchart = this;
        
        var colors = eval(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getColors()());
        
        var data = eval(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getData()());
        var serieNames = eval(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getSerieNames()());
        
        var verticalOffset = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getVerticalOffset()();
        var horizontalOffset = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getHorizontalOffset()();
        
        var chartWidth = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getChartWidth()();
        var chartHeight = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getChartHeight()();
        
        var legendSize = 0;
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isLegendEnabled()()) {
            legendSize = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getLegendSize()();
        }
        
        var vis = new $wnd.pv.Panel().canvas(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getDivId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
        
        var groupWidths = eval(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getGroupWidth()());
        
        //Value axis management
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isValueAxisEnabled()()) {
            var ruleMax = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getValueAxisLabelMax()();
            var ruleStep = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getValueAxisLabelStep()();
            var rule = vis.add($wnd.pv.Rule).data($wnd.pv.range(0, ruleMax, ruleStep));
            rule.bottom(function(d) { 
                return d * barHeight + verticalOffset;
            });
            rule.left(horizontalOffset - 20);
            rule.width(chartWidth - legendSize - horizontalOffset + 30);
            rule.strokeStyle("rgba(0,0,0,.2)");
            rule.textStyle("rgba(0,0,0,.5)");
            if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isValueAxisLabelEnabled()()) {
                rule.text(function(d) {
                    return vbarchart.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getValueAxisLabelValue(D)(d);
                });
                rule.add($wnd.pv.Label).textAlign("right").textBaseline("middle");
            }
        }
        
        var bar = vis.add($wnd.pv.Panel).def("active", false).def("activeIndex", -1).data(data).data(data).left(function(){return groupWidths[this.index][0]}).width(function(){return groupWidths[this.index][1]}).add($wnd.pv.Bar).data(function(d){ return d});
        
        var barWidth = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getBarWidth()();
        var barHeight = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getBarHeight()();
        var barInset = this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getBarInset()();
        
        bar.bottom(verticalOffset).width(barWidth - barInset).height(function(d){ return d * barHeight;}).left(function(){return this.index * barWidth});
        
        //Color management
        bar.fillStyle(function() {
        
            if (this.parent.active() && this.parent.activeIndex() == this.index) {
                return $wnd.pv.color(colors.range()[this.index]).brighter(0.5);
            } else {
                return $wnd.pv.color(colors.range()[this.index]);
            }
        });
        //bar.fillStyle(colors.by($wnd.pv.index));
        
        //Group axis management
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isGroupAxisEnabled()()) {
            var rule = vis.add($wnd.pv.Rule); 
            
            rule.bottom(verticalOffset).width(chartWidth - legendSize - horizontalOffset + 30).left(horizontalOffset - 20);
            rule.strokeStyle("rgba(0,0,0,.2)");
        }
        
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isGroupAxisLabelEnabled()()) {
        
            var tick = vis.add($wnd.pv.Rule); 
            tick.data(data).left(function(){return groupWidths[this.index][0] + (groupWidths[this.index][1] / 2 - 1);});
            tick.bottom(verticalOffset - 3).height(3);
            tick.strokeStyle("rgba(0,0,0,.5)");
        
            var label = bar.parent.anchor('bottom').add($wnd.pv.Label);
            
            label.textMargin(3);
            label.textStyle("rgba(0,0,0,.5)");
            
            label.text(function(){
                return vbarchart.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getGroupName(I)(this.parent.index);
            });
        }
        
        //Event management
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isTooltipEnabled()()) {
            var tooltips = eval(this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::getTooltips()());
            bar.event('mouseover', function() {
                var left = this.left() + this.parent.left();
                var top = this.top() + this.parent.top();
                var width = this.width();
                var tooltip = tooltips[this.parent.index][this.index];
                vbarchart.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::showTooltip(DDDLjava/lang/String;)(left, top, width, tooltip);
                
                this.parent.active(true);
                this.parent.activeIndex(this.index);
                
                return this;
            });
            bar.event('mouseout', function() {
                vbarchart.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::hideTooltip()();
                this.parent.active(false);
                this.parent.activeIndex(this.index);
                return this;
            });
        }
        
        
        //Legend management
        if (this.@com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent::isLegendEnabled()()) {
            var legend = vis.add($wnd.pv.Dot).data(serieNames);
            legend.top(function() {
                return (chartHeight - serieNames.length * 26)/2 + ((this.index) * 26);
            });
            legend.right(legendSize - 40);
            legend.size(50).strokeStyle(null).shape('square').fillStyle(colors.by($wnd.pv.index)).anchor("right").add($wnd.pv.Label);
        }
        
        vis.render();
        
    }-*/;
    
    public void showTooltip(double left, double top, double barWidth, String tooltipText) {
    	int arrowOffset = 10;
    	int tooltipLeft = this.getElement().getAbsoluteLeft() + Double.valueOf(left).intValue();
    	int tooltipTop = this.getElement().getAbsoluteTop() + Double.valueOf(top).intValue();
    	
    	if (this.currentTooltip == null) {
    		this.currentTooltip = new Tooltip();
    	}
    	this.currentTooltip.setText(tooltipText);
    	this.currentTooltip.initArrows();
    	
    	//Tooltip location calculation
    	this.currentTooltip.show();
    	//TODO: manage left and right
    	if (top < this.currentTooltip.getOffsetHeight()) {
    		//Display at the right
    		this.currentTooltip.setArrowStyle(ArrowStyle.LEFT);
    		this.currentTooltip.setPopupPosition(tooltipLeft + Double.valueOf(barWidth).intValue() + 2, tooltipTop);
    	} else {
    		this.currentTooltip.setArrowStyle(ArrowStyle.BOTTOM);
    		this.currentTooltip.setPopupPosition(tooltipLeft - arrowOffset+ (Double.valueOf(barWidth).intValue() / 2), tooltipTop - this.currentTooltip.getOffsetHeight());
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
    
    public double getChartWidth() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_WIDTH);
    }
    
    public double getChartHeight() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_HEIGHT);
    }
    
    public double getBarWidth() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BAR_WIDTH);
    }
    
    public double getBarHeight() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BAR_HEIGHT);
    }
    
    public double getBarInset() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_BAR_INSET);
    }
    
    public String getGroupWidth() {
    	return this.currentUIDL.getStringVariable(UIDL_OPTIONS_GROUP_WIDTH);
    }
    
    public double getGroupBarInset() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_GROUP_INSET);
    }
    
    public double getVerticalOffset() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_VERTICAL_OFFSET);
    }
    
    public double getHorizontalOffset() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_HORIZONTAL_OFFSET);
    }
    
    public boolean isGroupAxisEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_GROUP_AXIS_ENABLED);
    }
    
    public boolean isGroupAxisLabelEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_GROUP_AXIS_LABEL_ENABLED);
    }
    
    public String getGroupName(int index) {
    	return this.currentUIDL.getStringVariable(UIDL_DATA_GROUP_NAME + index);
    }
    
    public boolean isValueAxisEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_VALUE_AXIS_ENABLED);
    }
    
    public boolean isValueAxisLabelEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_VALUE_AXIS_LABEL_ENABLED);
    }
    
    public double getValueAxisLabelMax() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_VALUE_AXIS_LABEL_MAX);
    }
    
    public double getValueAxisLabelStep() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_VALUE_AXIS_LABEL_STEP);
    }
    
    public String getValueAxisLabelValue(double value) {
    	return this.labelFormat.format(value) + this.currentUIDL.getStringVariable(UIDL_OPTIONS_VALUE_AXIS_LABEL_UNIT);
    }
    
    public boolean isLegendEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_LEGEND_ENABLED);
    }
    
    public double getLegendSize() {
    	return this.currentUIDL.getDoubleVariable(UIDL_OPTIONS_LEGEND_SIZE);
    }
    
    public boolean isTooltipEnabled() {
    	return this.currentUIDL.getBooleanVariable(UIDL_OPTIONS_TOOLTIP_ENABLED);
    }
    
    public boolean isColorEnabled() {
        return this.currentUIDL.getStringArrayVariable(UIDL_OPTIONS_COLORS).length > 0;
    }
    
    public String getColors() {
        
        if (this.isColorEnabled()) {
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
        } else {
            return "$wnd.pv.Colors.category20()";
        }
        
    }
    
    
    
    public String getData() {
    	StringBuilder ret = new StringBuilder("[");
    	
    	int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_GROUPS_COUNT);
    	
    	for (int i = 0; i < serieCount; i++) {
    		
    		if (i > 0) {
    			ret.append(", ");
    		}
    		ret.append("[");
    		
			String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_GROUP_VALUES + i);
			
			for (int j = 0; j < values.length; j++) {
				if (j > 0) {
	    			ret.append(", ");
	    		}
				
				ret.append(values[j]);
			}
			ret.append("]");
		}
    	
    	ret.append("]");

    	return ret.toString();
    };
    
    public String getTooltips() {
    	StringBuilder ret = new StringBuilder("[");
    	
    	int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_GROUPS_COUNT);
    	
    	for (int i = 0; i < serieCount; i++) {
    		
    		if (i > 0) {
    			ret.append(", ");
    		}
    		ret.append("[");
    		
			String[] values = this.currentUIDL.getStringArrayVariable(UIDL_DATA_GROUP_TOOLTIP_VALUES + i);
			
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
    
    public String getSerieNames() {
    	
    	int serieCount = this.currentUIDL.getIntVariable(UIDL_DATA_SERIES_COUNT);
    	
    	
    	
    	StringBuilder ret = new StringBuilder("[");
    	
    	for (int i = 0; i < serieCount; i++) {
			if (i > 0) {
				ret.append(", ");
			}
			ret.append("'").append(this.currentUIDL.getStringVariable(UIDL_DATA_SERIE_NAME + i)).append("'");
		}
    	ret.append("]");
    	
    	return ret.toString();
    }
}
