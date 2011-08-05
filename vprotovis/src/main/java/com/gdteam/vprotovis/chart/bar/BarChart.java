package com.gdteam.vprotovis.chart.bar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BarChart implements Serializable {

	private double width = 150, height = 150;
	private List<Serie> series = new ArrayList<Serie>();
	private String[] groupNames;
	private double groupBarInset = 10;
	private double barInset = 2;
	private double barHeight = -1, barWidth = -1;
	private boolean groupAxisEnabled = false;
	private boolean groupAxisLabelEnabled = false;
	private double groupAxisLabelSize = 0;
	private boolean valueAxisEnabled = false;
	private boolean valueAxisLabelEnabled = false;
	private double valueAxisLabelSize = 0;
	private double valueAxisLabelMax = 0;
	private double valueAxisLabelStep = 0;
	private String valueAxisLabelPattern = "#.#";
	private String valueAxisLabelUnit = null;
	private boolean legendEnabled = false;
	private double legendSize = 0;
	private boolean tooltipEnabled = true;
	private String[] colors = new String[0];
	
	/**
	 * @return the groupBarInset
	 */
	public double getGroupBarInset() {
		return groupBarInset;
	}
	/**
	 * @param groupBarInset the groupBarInset to set
	 */
	public void setGroupBarInset(double groupBarInset) {
		this.groupBarInset = groupBarInset;
	}
	/**
	 * @return the barInset
	 */
	public double getBarInset() {
		return barInset;
	}
	/**
	 * @param barInset the barInset to set
	 */
	public void setBarInset(double barInset) {
		this.barInset = barInset;
	}
	/**
	 * @return the barHeight
	 */
	public double getBarHeight() {
		return barHeight;
	}
	/**
	 * @param barHeight the barHeight to set
	 */
	public void setBarHeight(double barHeight) {
		this.barHeight = barHeight;
	}
	/**
	 * @return the barWidth
	 */
	public double getBarWidth() {
		return barWidth;
	}
	/**
	 * @param barWidth the barWidth to set
	 */
	public void setBarWidth(double barWidth) {
		this.barWidth = barWidth;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	public int addSerie(String name, double[] values) {
		Serie s = new Serie();
		s.setName(name);
		s.setValues(values);
		this.series.add(s);
		return this.series.indexOf(s);
	}
	/**
	 * @return the series
	 */
	public List<Serie> getSeries() {
		return series;
	}
	/**
	 * @return the groupNames
	 */
	public String[] getGroupNames() {
		return groupNames;
	}
	/**
	 * @param groupNames the groupNames to set
	 */
	public void setGroupNames(String[] groupNames) {
		this.groupNames = groupNames;
	}
	/**
	 * @return the groupAxisEnabled
	 */
	public boolean isGroupAxisEnabled() {
		return groupAxisEnabled;
	}
	/**
	 * @param groupAxisEnabled the groupAxisEnabled to set
	 */
	public void setGroupAxisEnabled(boolean groupAxisEnabled) {
		this.groupAxisEnabled = groupAxisEnabled;
	}
	/**
	 * @return the groupAxisLabelEnabled
	 */
	public boolean isGroupAxisLabelEnabled() {
		return groupAxisLabelEnabled;
	}
	/**
	 * @param groupAxisLabelEnabled the groupAxisLabelEnabled to set
	 */
	public void setGroupAxisLabelEnabled(boolean groupAxisLabelEnabled) {
		this.groupAxisLabelEnabled = groupAxisLabelEnabled;
	}
	/**
	 * @return the groupAxisLabelSize
	 */
	public double getGroupAxisLabelSize() {
		return groupAxisLabelSize;
	}
	/**
	 * @param groupAxisLabelSize the groupAxisLabelSize to set
	 */
	public void setGroupAxisLabelSize(double groupAxisLabelSize) {
		this.groupAxisLabelSize = groupAxisLabelSize;
	}
	/**
	 * @return the valueAxisLabelSize
	 */
	public double getValueAxisLabelSize() {
		return valueAxisLabelSize;
	}
	/**
	 * @param valueAxisLabelSize the valueAxisLabelSize to set
	 */
	public void setValueAxisLabelSize(double valueAxisLabelSize) {
		this.valueAxisLabelSize = valueAxisLabelSize;
	}
	/**
	 * @return the valueAxisEnabled
	 */
	public boolean isValueAxisEnabled() {
		return valueAxisEnabled;
	}
	/**
	 * @param valueAxisEnabled the valueAxisEnabled to set
	 */
	public void setValueAxisEnabled(boolean valueAxisEnabled) {
		this.valueAxisEnabled = valueAxisEnabled;
	}
	/**
	 * @return the valueAxisLabelEnabled
	 */
	public boolean isValueAxisLabelEnabled() {
		return valueAxisLabelEnabled;
	}
	/**
	 * @param valueAxisLabelEnabled the valueAxisLabelEnabled to set
	 */
	public void setValueAxisLabelEnabled(boolean valueAxisLabelEnabled) {
		this.valueAxisLabelEnabled = valueAxisLabelEnabled;
	}
	/**
	 * @return the valueAxisLabelPattern
	 */
	public String getValueAxisLabelPattern() {
		return valueAxisLabelPattern;
	}
	/**
	 * @param valueAxisLabelPattern the valueAxisLabelPattern to set
	 */
	public void setValueAxisLabelPattern(String valueAxisLabelPattern) {
		this.valueAxisLabelPattern = valueAxisLabelPattern;
	}
	/**
	 * @return the valueAxisLabelUnit
	 */
	public String getValueAxisLabelUnit() {
		return valueAxisLabelUnit;
	}
	/**
	 * @param valueAxisLabelUnit the valueAxisLabelUnit to set
	 */
	public void setValueAxisLabelUnit(String valueAxisLabelUnit) {
		this.valueAxisLabelUnit = valueAxisLabelUnit == null ? "" : valueAxisLabelUnit;
	}
	/**
	 * @return the valueAxisLabelMax
	 */
	public double getValueAxisLabelMax() {
		return valueAxisLabelMax;
	}
	/**
	 * @param valueAxisLabelMax the valueAxisLabelMax to set
	 */
	public void setValueAxisLabelMax(double valueAxisLabelMax) {
		this.valueAxisLabelMax = valueAxisLabelMax;
	}
	/**
	 * @return the valueAxisLabelStep
	 */
	public double getValueAxisLabelStep() {
		return valueAxisLabelStep;
	}
	/**
	 * @param valueAxisLabelStep the valueAxisLabelStep to set
	 */
	public void setValueAxisLabelStep(double valueAxisLabelStep) {
		this.valueAxisLabelStep = valueAxisLabelStep;
	}
	/**
	 * @return the legendEnabled
	 */
	public boolean isLegendEnabled() {
		return legendEnabled;
	}
	/**
	 * @param legendEnabled the legendEnabled to set
	 */
	public void setLegendEnabled(boolean legendEnabled) {
		this.legendEnabled = legendEnabled;
	}
	/**
	 * @return the legendSize
	 */
	public double getLegendSize() {
		return legendSize;
	}
	/**
	 * @param legendSize the legendSize to set
	 */
	public void setLegendSize(double legendSize) {
		this.legendSize = legendSize;
	}
	/**
	 * @return the tooltipEnabled
	 */
	public boolean isTooltipEnabled() {
		return tooltipEnabled;
	}
	/**
	 * @param tooltipEnabled the tooltipEnabled to set
	 */
	public void setTooltipEnabled(boolean tooltipEnabled) {
		this.tooltipEnabled = tooltipEnabled;
	}
    /**
     * @return the colors
     */
    public String[] getColors() {
        return colors;
    }
    /**
     * @param colors the colors to set
     */
    public void setColors(String[] colors) {
        if (colors == null) {
            this.colors = new String[0];
        } else {
            this.colors = colors;
        }
    }
}
