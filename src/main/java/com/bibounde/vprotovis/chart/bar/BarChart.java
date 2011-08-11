package com.bibounde.vprotovis.chart.bar;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.gwt.util.ColorUtil;

public class BarChart {

    private double width = 150, height = 150;
    private List<Serie> series = new ArrayList<Serie>();
    private String[] groupNames;
    private double groupBarInset = 10d;
    private double barInset = 2d;
    private double marginLeft = 10d, marginRight = 10d, marginTop = 10d, marginBottom = 10d;
    private boolean horizontalAxisEnabled = false;
    private boolean horizontalAxisLabelEnabled = false;
    private boolean verticalAxisEnabled = false;
    private boolean verticalAxisLabelEnabled = false;
    private double verticalAxisLabelStart = 0d;
    private double verticalAxisLabelStop = 0d;
    private double verticalAxisLabelStep = 0d;
    private boolean verticalAxisGridEnabled = false;
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
     * @return the horizontalAxisEnabled
     */
    public boolean isHorizontalAxisEnabled() {
        return horizontalAxisEnabled;
    }
    /**
     * @param horizontalAxisEnabled the horizontalAxisEnabled to set
     */
    public void setHorizontalAxisEnabled(boolean horizontalAxisEnabled) {
        this.horizontalAxisEnabled = horizontalAxisEnabled;
    }
    /**
     * @return the horizontalAxisLabelEnabled
     */
    public boolean isHorizontalAxisLabelEnabled() {
        return horizontalAxisLabelEnabled;
    }
    /**
     * @param horizontalAxisLabelEnabled the horizontalAxisLabelEnabled to set
     */
    public void setHorizontalAxisLabelEnabled(boolean horizontalAxisLabelEnabled) {
        this.horizontalAxisLabelEnabled = horizontalAxisLabelEnabled;
    }
    /**
     * @return the verticalAxisEnabled
     */
    public boolean isVerticalAxisEnabled() {
        return verticalAxisEnabled;
    }
    /**
     * @param verticalAxisEnabled the verticalAxisEnabled to set
     */
    public void setVerticalAxisEnabled(boolean verticalAxisEnabled) {
        this.verticalAxisEnabled = verticalAxisEnabled;
    }
    /**
     * @return the verticalAxisLabelEnabled
     */
    public boolean isVerticalAxisLabelEnabled() {
        return verticalAxisLabelEnabled;
    }
    /**
     * @param verticalAxisLabelEnabled the verticalAxisLabelEnabled to set
     */
    public void setVerticalAxisLabelEnabled(boolean verticalAxisLabelEnabled) {
        this.verticalAxisLabelEnabled = verticalAxisLabelEnabled;
    }
    /**
     * @return the verticalAxisLabelStart
     */
    public double getVerticalAxisLabelStart() {
        return verticalAxisLabelStart;
    }
    /**
     * @param verticalAxisLabelStart the verticalAxisLabelStart to set
     */
    public void setVerticalAxisLabelStart(double verticalAxisLabelStart) {
        this.verticalAxisLabelStart = verticalAxisLabelStart;
    }
    /**
     * @return the verticalAxisLabelStop
     */
    public double getVerticalAxisLabelStop() {
        return verticalAxisLabelStop;
    }
    /**
     * @param verticalAxisLabelStop the verticalAxisLabelStop to set
     */
    public void setVerticalAxisLabelStop(double verticalAxisLabelStop) {
        this.verticalAxisLabelStop = verticalAxisLabelStop;
    }
    /**
     * @return the verticalAxisLabelStep
     */
    public double getVerticalAxisLabelStep() {
        return verticalAxisLabelStep;
    }
    /**
     * @param verticalAxisLabelStep the verticalAxisLabelStep to set
     */
    public void setVerticalAxisLabelStep(double verticalAxisLabelStep) {
        this.verticalAxisLabelStep = verticalAxisLabelStep;
    }
    /**
     * @return the verticalAxisGridEnabled
     */
    public boolean isVerticalAxisGridEnabled() {
        return verticalAxisGridEnabled;
    }
    /**
     * @param verticalAxisGridEnabled the verticalAxisGridEnabled to set
     */
    public void setVerticalAxisGridEnabled(boolean verticalAxisGridEnabled) {
        this.verticalAxisGridEnabled = verticalAxisGridEnabled;
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
