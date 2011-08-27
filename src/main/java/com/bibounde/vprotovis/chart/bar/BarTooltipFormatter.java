package com.bibounde.vprotovis.chart.bar;

import java.io.Serializable;

/**
 * Identifies components that can be used to format tooltip content
 * @author bibounde
 *
 */
public interface BarTooltipFormatter extends Serializable {

    /**
     * Returns true if tooltip must be visible for the specified value, serie name, and group name
     * @param serieName serie name which is selected
     * @param value selected value
     * @param groupName selected group
     * @return true if tooltip must be visible for the specified value and serie name, and group name
     */
    boolean isVisible(String serieName, double value, String groupName);
    
    
    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @param groupName selected group
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, double value, String groupName);
}
