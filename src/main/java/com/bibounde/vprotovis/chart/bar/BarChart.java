package com.bibounde.vprotovis.chart.bar;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.AbstractChart;

public class BarChart extends AbstractChart {

    private List<Serie> series = new ArrayList<Serie>();
    private String[] groupNames;
    private double groupInset = 25d;
    private double barInset = 2d;
    private boolean xAxisEnabled = false;
    private boolean xAxisLabelEnabled = false;
    private boolean yAxisEnabled = false;
    private boolean yAxisLabelEnabled = false;
    private double yAxisLabelStep = 0d;
    private boolean yAxisGridEnabled = false;
    
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
     * @return the groupInset
     */
    public double getGroupInset() {
        return groupInset;
    }
    /**
     * @param groupInset the groupInset to set
     */
    public void setGroupInset(double groupBarInset) {
        this.groupInset = groupBarInset;
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

    public double getLineWidth() {
        throw new RuntimeException("Not yet implemented");
    }

    public void setLineWidth(double lineWidth) {
        throw new RuntimeException("Not yet implemented");
    }
}
