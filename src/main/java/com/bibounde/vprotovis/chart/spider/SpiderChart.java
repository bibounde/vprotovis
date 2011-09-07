package com.bibounde.vprotovis.chart.spider;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.AbstractChart;

public class SpiderChart extends AbstractChart {

    private List<Serie> series = new ArrayList<Serie>();
    private String[] axisNames;
    private boolean axisEnabled = true;
    private double axisOffset = 10d;
    private boolean axisLabelEnabled = true;
    private boolean axisCaptionEnabled = true;
    private double axisLabelStep = 1d;
    private boolean axisGridEnabled = true;
    private int lineWidth = 1;
    private boolean areaMode = true;
    private double opacity = 0.3d;
    
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
    
    public int addSerie(String name, double[] values) {
        Serie s = new Serie();
        s.setName(name);
        s.setValues(values);
        this.series.add(s);
        return this.series.indexOf(s);
    }
    
    /**
     * @return the axisNames
     */
    public String[] getAxisNames() {
        return axisNames;
    }
    /**
     * @param axisNames the axisNames to set
     */
    public void setAxisNames(String[] axisNames) {
        this.axisNames = axisNames;
    }
    /**
     * @return the axisEnabled
     */
    public boolean isAxisEnabled() {
        return axisEnabled;
    }
    /**
     * @param axisEnabled the axisEnabled to set
     */
    public void setAxisEnabled(boolean axisEnabled) {
        this.axisEnabled = axisEnabled;
    }
    /**
     * @return the axisLabelEnabled
     */
    public boolean isAxisLabelEnabled() {
        return axisLabelEnabled;
    }
    /**
     * @param axisLabelEnabled the axisLabelEnabled to set
     */
    public void setAxisLabelEnabled(boolean axisLabelEnabled) {
        this.axisLabelEnabled = axisLabelEnabled;
    }
    /**
     * @return the axisLabelStep
     */
    public double getAxisLabelStep() {
        return axisLabelStep;
    }
    /**
     * @param axisLabelStep the axisLabelStep to set
     */
    public void setAxisLabelStep(double axisLabelStep) {
        this.axisLabelStep = axisLabelStep;
    }
    /**
     * @return the axisGridEnabled
     */
    public boolean isAxisGridEnabled() {
        return axisGridEnabled;
    }
    /**
     * @param axisGridEnabled the axisGridEnabled to set
     */
    public void setAxisGridEnabled(boolean axisGridEnabled) {
        this.axisGridEnabled = axisGridEnabled;
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
     * @return the areaMode
     */
    public boolean isAreaMode() {
        return areaMode;
    }
    /**
     * @param areaMode the areaMode to set
     */
    public void setAreaMode(boolean areaMode) {
        this.areaMode = areaMode;
    }
    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }
    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }
    /**
     * @return the axisOffset
     */
    public double getAxisOffset() {
        return axisOffset;
    }
    /**
     * @param axisOffset the axisOffset to set
     */
    public void setAxisOffset(double axisOffset) {
        this.axisOffset = axisOffset;
    }
    /**
     * @return the axisCaptionEnabled
     */
    public boolean isAxisCaptionEnabled() {
        return axisCaptionEnabled;
    }
    /**
     * @param axisCaptionEnabled the axisCaptionEnabled to set
     */
    public void setAxisCaptionEnabled(boolean axisCaptionEnabled) {
        this.axisCaptionEnabled = axisCaptionEnabled;
    }
}
