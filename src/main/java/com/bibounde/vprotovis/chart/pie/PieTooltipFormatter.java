package com.bibounde.vprotovis.chart.pie;

import java.io.Serializable;

/**
 * Identifies components that can be used to format pie tooltip value
 * 
 * @author bibounde
 * 
 */
public interface PieTooltipFormatter extends Serializable {

    /**
     * Returns true if tooltip must be visible for the specified value and serie name
     * @param value value to display in tooltip
     * @param serieName serie name
     * @return true if tooltip must be visible for the specified value and serie name
     */
    boolean isVisible(String serieName, double value);
    
    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, double value);
}
