package com.bibounde.vprotovis.chart.pie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.util.ColorUtil;

public class PieChart implements Serializable {

    private static final String COLOR_BLACK = "#000000";
    private static final String COLOR_WHITE = "#FFFFFF";
    
    private double width = 150, height = 150;
    private double highlightOffset = 10d;
    private List<Serie> series = new ArrayList<Serie>();
    private double marginLeft = 10d, marginRight = 10d, marginTop = 10d, marginBottom = 10d;
    private String[] colors = ColorUtil.getDefaultColors();
    private boolean legendEnabled = false;
    private double legendAreaWidth = 0d;
    private boolean labelVisible = false;
    private String labelColor = COLOR_BLACK;
    private boolean tooltipEnabled = true;
    private boolean tooltipPermanent = false;
    private int lineWidth = 0;
    private String lineColor= COLOR_WHITE;
    
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
    
    public int addSerie(String name, double value, boolean highlight) {
        Serie s = new Serie();
        s.setName(name);
        s.setValue(value);
        s.setHighlight(highlight);
        this.series.add(s);
        return this.series.indexOf(s);
    }
    
    /**
     * @param series the series to set
     */
    public void setSeries(List<Serie> series) {
        this.series = series;
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
     * @return the highlightOffset
     */
    public double getHighlightOffset() {
        return highlightOffset;
    }
    /**
     * @param highlightOffset the highlightOffset to set
     */
    public void setHighlightOffset(double highlightOffset) {
        this.highlightOffset = highlightOffset;
    }
    /**
     * @return the labelVisible
     */
    public boolean isLabelVisible() {
        return labelVisible;
    }
    /**
     * @param labelVisible the labelVisible to set
     */
    public void setLabelVisible(boolean labelVisible) {
        this.labelVisible = labelVisible;
    }
    /**
     * @return the labelColor
     */
    public String getLabelColor() {
        return labelColor;
    }
    /**
     * @param labelColor the labelColor to set
     */
    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor == null ? COLOR_BLACK : labelColor;
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
     * @return the tooltipPermanent
     */
    public boolean isTooltipPermanent() {
        return tooltipPermanent;
    }
    /**
     * @param tooltipPermanent the tooltipPermanent to set
     */
    public void setTooltipPermanent(boolean tooltipPermanent) {
        this.tooltipPermanent = tooltipPermanent;
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
     * @return the lineColor
     */
    public String getLineColor() {
        return lineColor;
    }
    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(String lineColor) {
        this.lineColor = lineColor == null ? COLOR_WHITE : lineColor;
    }
}
