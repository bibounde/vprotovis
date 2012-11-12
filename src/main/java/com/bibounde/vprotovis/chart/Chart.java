package com.bibounde.vprotovis.chart;

import java.io.Serializable;

public interface Chart extends Serializable {

    /**
     * @return the width
     */
    public double getWidth();

    /**
     * @param width the width to set
     */
    public void setWidth(double width);

    /**
     * @return the height
     */
    public double getHeight();

    /**
     * @param height the height to set
     */
    public void setHeight(double height);

    /**
     * @return the marginLeft
     */
    public double getMarginLeft();

    /**
     * @param marginLeft the marginLeft to set
     */
    public void setMarginLeft(double marginLeft);

    /**
     * @return the marginRight
     */
    public double getMarginRight();

    /**
     * @param marginRight the marginRight to set
     */
    public void setMarginRight(double marginRight);

    /**
     * @return the marginTop
     */
    public double getMarginTop();

    /**
     * @param marginTop the marginTop to set
     */
    public void setMarginTop(double marginTop);

    /**
     * @return the marginBottom
     */
    public double getMarginBottom();

    /**
     * @param marginBottom the marginBottom to set
     */
    public void setMarginBottom(double marginBottom);

    /**
     * @return the colors
     */
    public String[] getColors();

    /**
     * @param colors the colors to set
     */
    public void setColors(String[] colors);

    /**
     * @return the legendEnabled
     */
    public boolean isLegendEnabled();

    /**
     * @param legendEnabled the legendEnabled to set
     */
    public void setLegendEnabled(boolean legendEnabled);

    /**
     * @return the legendAreaWidth
     */
    public double getLegendAreaWidth();

    /**
     * @param legendAreaWidth the legendAreaWidth to set
     */
    public void setLegendAreaWidth(double legendAreaWidth);
    
    /**
     * Retrieves legend's left inset value
     * @return legend's left inset value
     */
    public double getLegendInsetLeft();
    
    /**
     * Sets legend's left inset value
     * @param inset legend's left inset value
     */
    public void setLegendInsetLeft(double inset); 

    /**
     * Returns the line width
     * @return line width
     */
    public double getLineWidth();

    /**
     * Sets the line width
     * @param lineWidth line width
     */
    public void setLineWidth(double lineWidth);

    /**
     * @return the tooltipEnabled
     */
    public boolean isTooltipEnabled();

    /**
     * @param tooltipEnabled the tooltipEnabled to set
     */
    public void setTooltipEnabled(boolean tooltipEnabled);

}