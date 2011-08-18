package com.bibounde.vprotovis.chart.line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.common.Point;
import com.bibounde.vprotovis.gwt.util.ColorUtil;

public class LineChart implements Serializable {

	private double width = 150, height = 150;
	private List<Serie> series = new ArrayList<Serie>();
	private boolean xAxisEnabled = false;
	private boolean xAxisLabelEnabled = false;
	private double xAxisLabelStep = 0d;
	private boolean xAxisGridEnabled = false;
	private double marginLeft = 10d, marginRight = 10d, marginTop = 10d, marginBottom = 10d;
	private boolean yAxisEnabled = false;
	private boolean yAxisLabelEnabled = false;
	private double yAxisLabelStep = 0d;
	private boolean yAxisGridEnabled = false;
	private InterpolationMode interpolationMode = InterpolationMode.LINEAR;
	private int lineWidth = 1;
	private String[] colors = ColorUtil.getDefaultColors();
	private boolean legendEnabled = false;
	private double legendAreaWidth = 0d;
	
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
	
	public int addSerie(String name, Point[] values) {
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
	 * @param series the series to set
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
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
		this.colors = colors;
	}
    /**
     * @return the xAxisEnabled
     */
    public boolean isXAxisEnabled() {
        return xAxisEnabled;
    }
    /**
     * @param xAxisEnabled the xAxisEnabled to set
     */
    public void setXAxisEnabled(boolean xAxisEnabled) {
        this.xAxisEnabled = xAxisEnabled;
    }
    /**
     * @return the xAxisLabelEnabled
     */
    public boolean isXAxisLabelEnabled() {
        return xAxisLabelEnabled;
    }
    /**
     * @param xAxisLabelEnabled the xAxisLabelEnabled to set
     */
    public void setXAxisLabelEnabled(boolean xAxisLabelEnabled) {
        this.xAxisLabelEnabled = xAxisLabelEnabled;
    }
    
    /**
     * @return the xAxisLabelStep
     */
    public double getXAxisLabelStep() {
        return xAxisLabelStep;
    }
    /**
     * @param xAxisLabelStep the xAxisLabelStep to set
     */
    public void setXAxisLabelStep(double xAxisLabelStep) {
        this.xAxisLabelStep = xAxisLabelStep;
    }
    /**
     * @return the marginLeft
     */
    public double getMarginLeft() {
        return marginLeft;
    }
    /**
     * @param marginLeft the marginLeft to set
     */
    public void setMarginLeft(double marginLeft) {
        this.marginLeft = marginLeft;
    }
    /**
     * @return the marginRight
     */
    public double getMarginRight() {
        return marginRight;
    }
    /**
     * @param marginRight the marginRight to set
     */
    public void setMarginRight(double marginRight) {
        this.marginRight = marginRight;
    }
    /**
     * @return the marginTop
     */
    public double getMarginTop() {
        return marginTop;
    }
    /**
     * @param marginTop the marginTop to set
     */
    public void setMarginTop(double marginTop) {
        this.marginTop = marginTop;
    }
    /**
     * @return the marginBottom
     */
    public double getMarginBottom() {
        return marginBottom;
    }
    /**
     * @param marginBottom the marginBottom to set
     */
    public void setMarginBottom(double marginBottom) {
        this.marginBottom = marginBottom;
    }
    /**
     * @return the yAxisEnabled
     */
    public boolean isYAxisEnabled() {
        return yAxisEnabled;
    }
    /**
     * @param yAxisEnabled the yAxisEnabled to set
     */
    public void setYAxisEnabled(boolean yAxisEnabled) {
        this.yAxisEnabled = yAxisEnabled;
    }
    /**
     * @return the yAxisLabelEnabled
     */
    public boolean isYAxisLabelEnabled() {
        return yAxisLabelEnabled;
    }
    /**
     * @param yAxisLabelEnabled the yAxisLabelEnabled to set
     */
    public void setYAxisLabelEnabled(boolean yAxisLabelEnabled) {
        this.yAxisLabelEnabled = yAxisLabelEnabled;
    }
    
    /**
     * @return the yAxisLabelStep
     */
    public double getYAxisLabelStep() {
        return yAxisLabelStep;
    }
    /**
     * @param yAxisLabelStep the yAxisLabelStep to set
     */
    public void setYAxisLabelStep(double yAxisLabelStep) {
        this.yAxisLabelStep = yAxisLabelStep;
    }
    /**
     * @return the xAxisGridEnabled
     */
    public boolean isXAxisGridEnabled() {
        return xAxisGridEnabled;
    }
    /**
     * @param xAxisGridEnabled the xAxisGridEnabled to set
     */
    public void setXAxisGridEnabled(boolean xAxisGridEnabled) {
        this.xAxisGridEnabled = xAxisGridEnabled;
    }
    /**
     * @return the yAxisGridEnabled
     */
    public boolean isYAxisGridEnabled() {
        return yAxisGridEnabled;
    }
    /**
     * @param yAxisGridEnabled the yAxisGridEnabled to set
     */
    public void setYAxisGridEnabled(boolean yAxisGridEnabled) {
        this.yAxisGridEnabled = yAxisGridEnabled;
    }
    /**
     * @return the interpolationMode
     */
    public InterpolationMode getInterpolationMode() {
        return interpolationMode;
    }
    /**
     * @param interpolationMode the interpolationMode to set
     */
    public void setInterpolationMode(InterpolationMode interpolationMode) {
        this.interpolationMode = interpolationMode;
    }
    /**
     * @return the lineWidth
     */
    public int getLineWidth() {
        return lineWidth;
    }
    /**
     * @param lineWidth the lineWidth to set
     */
    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
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
     * @return the legendAreaWidth
     */
    public double getLegendAreaWidth() {
        return legendAreaWidth;
    }
    /**
     * @param legendAreaWidth the legendAreaWidth to set
     */
    public void setLegendAreaWidth(double legendAreaWidth) {
        this.legendAreaWidth = legendAreaWidth;
    }
	
}
