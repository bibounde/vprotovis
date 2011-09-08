package com.bibounde.vprotovis.chart.spider;

import java.io.Serializable;

/**
 * Identifies components that can be used to format tooltip content
 * @author bibounde
 *
 */
public interface SpiderTooltipFormatter extends Serializable{

    /**
     * Returns tooltip content as HTML
     * @param axisName axis name which is selected
     * @param serieName serie name which is selected
     * @param value selected value
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String axisName, String serieName, double value);
}
