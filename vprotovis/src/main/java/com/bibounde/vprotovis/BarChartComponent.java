package com.bibounde.vprotovis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bibounde.vprotovis.chart.bar.BarChart;
import com.bibounde.vprotovis.chart.bar.Serie;
import com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VBarChartComponent.class)
public class BarChartComponent extends AbstractComponent {

	private BarChart chart;
	private String id = "v-protovis-barchart-" + this.hashCode();
	
	public BarChartComponent() {
		chart = new BarChart();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int addSerie(String name, double[] values) {
		return this.chart.addSerie(name, values);
	}
	
	public void setGroupBarInset(double groupBarInset) {
		this.chart.setGroupBarInset(groupBarInset);
	}
	
	public void setBarInset(double barInset) {
		this.chart.setBarInset(barInset);
	}
	
	public void setBarWidth(double width) {
		this.chart.setBarWidth(width);
	}
	
	public void setBarHeight(double height) {
		this.chart.setBarHeight(height);
	}
	
	public void setChartWidth(double width) {
		this.chart.setWidth(width);
	}
	
	public void setChartHeight(double height) {
		this.chart.setHeight(height);
	}
	
	public String getTooltipHTML(int serieIndex, int valueIndex, String groupName) {
		Serie serie = this.chart.getSeries().get(serieIndex);
		
		StringBuilder ret = new StringBuilder();
		if (!"".equals(groupName)) {
			ret.append("<b>").append(groupName).append("</b><br/>");
		}
		ret.append(serie.getName()).append(": ").append(serie.getValues()[valueIndex]);
		
		return ret.toString();
	}
	
	public void setGroupNames(String[] names) {
		this.chart.setGroupNames(names);
	}
	
	public void addGroupAxis() {
		this.chart.setGroupAxisEnabled(true);
		this.chart.setGroupAxisLabelEnabled(false);
		
	}
	
	public void removeGroupAxis() {
        this.chart.setGroupAxisEnabled(false);
        this.chart.setGroupAxisLabelEnabled(false);
    }
	
	public void addGroupAxisWithLabel() {
    	this.addGroupAxisWithLabel(20);
    }
	
    public void addGroupAxisWithLabel(double labelAreaHeight) {
    	this.chart.setGroupAxisEnabled(true);
		this.chart.setGroupAxisLabelEnabled(true);
		if (labelAreaHeight > 0) {
			this.chart.setGroupAxisLabelSize(labelAreaHeight);
		}
    }
    
    public void removeGroupAxisLabels() {
        this.chart.setGroupAxisLabelEnabled(false);
    }
    
    public void addValueAxis() {
    	this.chart.setValueAxisEnabled(true);
    	this.chart.setValueAxisLabelEnabled(false);
    }
    
    public void removeValueAxis() {
        this.chart.setValueAxisEnabled(false);
        this.chart.setValueAxisLabelEnabled(false);
    }
    
    public void addValueAxisWithLabel(double labelAreaWidth, double max, double step, String numericalPattern) {
    	this.addValueAxisWithLabel(labelAreaWidth, max, step, numericalPattern, null);
    }

    public void addValueAxisWithLabel(double labelAreaWidth, double max, double step, String numericalPattern, String unit) {
    	this.chart.setValueAxisEnabled(true);
    	this.chart.setValueAxisLabelEnabled(true);
    	if (labelAreaWidth > 0) {
    		this.chart.setValueAxisLabelSize(labelAreaWidth);
    	}
    	this.chart.setValueAxisLabelPattern(numericalPattern);
    	this.chart.setValueAxisLabelUnit(unit);
    	this.chart.setValueAxisLabelMax(max);
    	this.chart.setValueAxisLabelStep(step);
    }
    
    public void removeValueAxisLabels() {
        this.chart.setValueAxisLabelEnabled(false);
    }
    
    public void addLegend(double legendAreaWidth) {
    	this.chart.setLegendEnabled(true);
    	this.chart.setLegendSize(legendAreaWidth);
    }
    
    public void removeLegend() {
        this.chart.setLegendEnabled(false);
    }
    
    public void setTooltipEnabled(boolean enabled) {
    	this.chart.setTooltipEnabled(enabled);
    }
    
    public void setColors(String[] colors) {
        this.chart.setColors(colors);
    }

	@Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addVariable(this, VBarChartComponent.UIDL_DIV_ID, this.id);
        
        int groupCount  = this.getGroupCount();
        
        this.paintChartValues(target, groupCount);
        this.paintChartOptions(target, groupCount);
    }
	
	private void paintChartValues(PaintTarget target, int groupCount) throws PaintException {
		
		target.addVariable(this, VBarChartComponent.UIDL_DATA_SERIES_COUNT, this.chart.getSeries().size());
		
		String[] groupNames = this.getGroupNames(groupCount);
		
		target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUPS_COUNT, groupCount);
		
		for (int i = 0; i < groupNames.length; i++) {
			target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_NAME + i, groupNames[i]);
		}
		
		//Convert data in protovis mode
		Map<Integer, List<String>> dataMap = new HashMap<Integer, List<String>>();
		Map<Integer, List<String>> tooltipMap = new HashMap<Integer, List<String>>();
		
		int serieIndex = 0;
		for (Serie serie : this.chart.getSeries()) {
			for (int i = 0; i < serie.getValues().length; i++) {
				//Data
				List<String> values = null;
				if (!dataMap.containsKey(i)) {
					values = new ArrayList<String>();
					dataMap.put(i, values);
				} else {
					values = dataMap.get(i);
				}
				values.add(String.valueOf(serie.getValues()[i]));
				
				//Tooltips
				List<String> tooltips = null;
				if (!tooltipMap.containsKey(i)) {
					tooltips = new ArrayList<String>();
					tooltipMap.put(i, tooltips);
				} else {
					tooltips = tooltipMap.get(i);
				}
				tooltips.add(this.getTooltipHTML(serieIndex, i, groupNames[i]));
				
			}
			
			target.addVariable(this, VBarChartComponent.UIDL_DATA_SERIE_NAME + serieIndex, serie.getName());
			serieIndex++;
		}
		
		//Data
		int index = 0;
		for (List<String> values : dataMap.values()) {
			target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_VALUES + index, values.toArray(new String[values.size()]));
			index++;
		}
		
		//Tooltips
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_TOOLTIP_ENABLED, this.chart.isTooltipEnabled());
		if (this.chart.isTooltipEnabled()) {
			index = 0;
			for (List<String> values : tooltipMap.values()) {
				target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_TOOLTIP_VALUES + index, values.toArray(new String[values.size()]));
				index++;
			}
		}
	}
	
	private void paintChartOptions(PaintTarget target, int groupCount) throws PaintException {
		
		double verticalOffset = this.getVerticalOffset();
		double horizontalOffset = this.getHorizontalOffset();
		double barWidth = this.chart.getBarWidth() > 0 ? this.chart.getBarWidth() : this.getAutoBarWidth(groupCount, horizontalOffset);
		double barHeight = this.chart.getBarHeight() > 0 ? this.chart.getBarHeight() : this.getAutoBarHeight(verticalOffset);
		
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_WIDTH, this.chart.getWidth());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_HEIGHT, this.chart.getHeight());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_WIDTH, barWidth);
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_HEIGHT, barHeight);
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_INSET, this.chart.getBarInset());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_INSET, this.chart.getGroupBarInset());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_AXIS_ENABLED, this.chart.isGroupAxisEnabled());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_AXIS_LABEL_ENABLED, this.chart.isGroupAxisLabelEnabled());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_ENABLED, this.chart.isValueAxisEnabled());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_LABEL_ENABLED, this.chart.isValueAxisLabelEnabled());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_LABEL_PATTERN, this.chart.getValueAxisLabelPattern());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_LABEL_UNIT, this.chart.getValueAxisLabelUnit());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_LABEL_MAX, this.chart.getValueAxisLabelMax());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VALUE_AXIS_LABEL_STEP, this.chart.getValueAxisLabelStep());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_LEGEND_SIZE, this.chart.getLegendSize());
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_OFFSET, verticalOffset);
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_HORIZONTAL_OFFSET, horizontalOffset);
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());
		
		//Calculation of group widths
		double left = horizontalOffset;
		StringBuilder groupWidths = new StringBuilder("[");
		
		for (int i = 0; i < groupCount; i++) {
			if (i > 0) {
				groupWidths.append(", ");
			}
			double  gWidth = this.getAutoGroupBarWidth(barWidth);
			groupWidths.append("[").append(left).append(", ").append(gWidth).append("]");
			
			left += gWidth + this.chart.getGroupBarInset();
		}
		groupWidths.append("]");
		
		target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_WIDTH, groupWidths.toString());
	}
	
	protected double getAutoBarWidth(int groupCount, double horizontalOffset) {
		if (this.chart.getSeries().size() == 0) {
			return 0;
		} else {
			double totalWidth = this.chart.getWidth() - ((groupCount - 1) * this.chart.getGroupBarInset()) - horizontalOffset;
			
			if (this.chart.isLegendEnabled()) {
				totalWidth -= this.chart.getLegendSize();
			}
			
			int barCount = this.getBarCount();
			
			if (barCount == 0) {
				return 0;
			} else {
				return totalWidth / barCount;
			}
		}
	}
	
	protected double getAutoBarHeight(double verticalOffset) {
		return (this.chart.getHeight() - verticalOffset) * 0.9d / this.getMaxValue();
	}
	
	protected double getAutoGroupBarWidth(double barWidth) {
		return barWidth * this.chart.getSeries().size();
	}
	
	protected double getMaxValue() {
		double max = 0;
		for (Serie serie : this.chart.getSeries()) {
			for (double value : serie.getValues()) {
				max = value > max ? value : max;
			}
		}
		return max;
	}
	
	protected int getBarCount() {
		int ret = 0;
		for (Serie serie : this.chart.getSeries()) {
			ret += serie.getValues().length;
		}
		return ret;
	}
	
	protected int getGroupCount() {
		int max = 0;
		for (Serie serie : this.chart.getSeries()) {
			max = serie.getValues().length > max ? serie.getValues().length : max;
		}
		return max;
	}
	
	protected String[] getGroupNames(int groupCount){
		
		String[] ret = new String[groupCount];
		
		if (this.chart.getGroupNames() == null) {
			for (int i = 0; i < groupCount; i++) {
				ret[i] = "";
			}
		} else {
			int namesCount = this.chart.getGroupNames().length; 
			for (int i = 0; i < groupCount; i++) {
				if (i < namesCount) {
					ret[i] = this.chart.getGroupNames()[i];
				} else {
					ret[i] = "";
				}
			}
		}
		
		return ret;
	}
	
	protected double getVerticalOffset() {
		double ret = this.chart.isGroupAxisEnabled() ? 1 : 0;
		if (this.chart.isGroupAxisLabelEnabled()) {
			ret += this.chart.getGroupAxisLabelSize();
		}
		
		return ret;
	}
	
	protected double getHorizontalOffset() {
		double ret = this.chart.isValueAxisEnabled() ? 1 + this.chart.getBarInset() : this.chart.getBarInset();
		if (this.chart.isValueAxisLabelEnabled()) {
			ret += this.chart.getValueAxisLabelSize();
		}
		return ret;
	}

	/**
	 * @return the chart
	 */
	public BarChart getChart() {
		return chart;
	}
}
