package com.bibounde.vprotovis.chart.pie;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.AbstractChart;

public class PieChart extends AbstractChart {

    private static final String COLOR_BLACK = "#000000";
    private static final String COLOR_WHITE = "#FFFFFF";
    
    private double highlightOffset = 10d;
    private List<Serie> series = new ArrayList<Serie>();
    private boolean labelVisible = false;
    private String labelColor = COLOR_BLACK;
    private boolean tooltipPermanent = false;
    private int lineWidth = 0;
    private String lineColor= COLOR_WHITE;
    
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
