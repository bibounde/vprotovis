package com.bibounde.vprotovis.chart.line;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.AbstractChart;
import com.bibounde.vprotovis.chart.InterpolationMode;
import com.bibounde.vprotovis.common.Point;

public class LineChart extends AbstractChart {

	private List<Serie> series = new ArrayList<Serie>();
	private boolean xAxisEnabled = false;
	private boolean xAxisLabelEnabled = false;
	private double xAxisLabelStep = 0d;
	private boolean xAxisGridEnabled = false;
	private boolean yAxisEnabled = false;
	private boolean yAxisLabelEnabled = false;
	private double yAxisLabelStep = 0d;
	private boolean yAxisGridEnabled = false;
	private InterpolationMode interpolationMode = InterpolationMode.LINEAR;
	private double lineWidth = 1;
	
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
    public double getLineWidth() {
        return lineWidth;
    }
    /**
     * @param lineWidth the lineWidth to set
     */
    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }
}
