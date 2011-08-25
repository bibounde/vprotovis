package com.bibounde.vprotovis.chart.bar;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import com.bibounde.vprotovis.util.ColorUtil;

public class BarChart implements Serializable {

    private double width = 150, height = 150;
    private List<Serie> series = new ArrayList<Serie>();
    private String[] groupNames;
    private double groupInset = 25d;
    private double barInset = 2d;
    private double marginLeft = 10d, marginRight = 10d, marginTop = 10d, marginBottom = 10d;
    private boolean xAxisEnabled = false;
    private boolean xAxisLabelEnabled = false;
    private boolean yAxisEnabled = false;
    private boolean yAxisLabelEnabled = false;
    private double yAxisLabelStep = 0d;
    private boolean yAxisGridEnabled = false;
    private String[] colors = ColorUtil.getDefaultColors();
    private boolean legendEnabled = false;
    private double legendAreaWidth = 0d;
    private boolean tooltipEnabled = true;
    
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
}
