package com.bibounde.vprotovis.chart;

import com.bibounde.vprotovis.util.ColorUtil;

public abstract class AbstractChart implements Chart {

    private double width = 150, height = 150;
    private double marginLeft = 10d, marginRight = 10d, marginTop = 10d, marginBottom = 10d;
    private String[] colors = ColorUtil.getDefaultColors();
    private boolean legendEnabled = false;
    private double legendAreaWidth = 0d;
    private boolean tooltipEnabled = true;
    
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getWidth()
     */
    @Override
    public double getWidth() {
        return width;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setWidth(double)
     */
    @Override
    public void setWidth(double width) {
        this.width = width;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getHeight()
     */
    @Override
    public double getHeight() {
        return height;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setHeight(double)
     */
    @Override
    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getMarginLeft()
     */
    @Override
    public double getMarginLeft() {
        return marginLeft;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setMarginLeft(double)
     */
    @Override
    public void setMarginLeft(double marginLeft) {
        this.marginLeft = marginLeft;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getMarginRight()
     */
    @Override
    public double getMarginRight() {
        return marginRight;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setMarginRight(double)
     */
    @Override
    public void setMarginRight(double marginRight) {
        this.marginRight = marginRight;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getMarginTop()
     */
    @Override
    public double getMarginTop() {
        return marginTop;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setMarginTop(double)
     */
    @Override
    public void setMarginTop(double marginTop) {
        this.marginTop = marginTop;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getMarginBottom()
     */
    @Override
    public double getMarginBottom() {
        return marginBottom;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setMarginBottom(double)
     */
    @Override
    public void setMarginBottom(double marginBottom) {
        this.marginBottom = marginBottom;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getColors()
     */
    @Override
    public String[] getColors() {
        return colors;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setColors(java.lang.String[])
     */
    @Override
    public void setColors(String[] colors) {
        this.colors = colors;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#isLegendEnabled()
     */
    @Override
    public boolean isLegendEnabled() {
        return legendEnabled;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setLegendEnabled(boolean)
     */
    @Override
    public void setLegendEnabled(boolean legendEnabled) {
        this.legendEnabled = legendEnabled;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#getLegendAreaWidth()
     */
    @Override
    public double getLegendAreaWidth() {
        return legendAreaWidth;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setLegendAreaWidth(double)
     */
    @Override
    public void setLegendAreaWidth(double legendAreaWidth) {
        this.legendAreaWidth = legendAreaWidth;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#isTooltipEnabled()
     */
    @Override
    public boolean isTooltipEnabled() {
        return tooltipEnabled;
    }
    /**
     * @see com.bibounde.vprotovis.chart.Chart#setTooltipEnabled(boolean)
     */
    @Override
    public void setTooltipEnabled(boolean tooltipEnabled) {
        this.tooltipEnabled = tooltipEnabled;
    }
}
