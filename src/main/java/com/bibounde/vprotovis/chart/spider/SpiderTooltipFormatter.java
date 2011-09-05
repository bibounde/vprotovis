package com.bibounde.vprotovis.chart.spider;

/**
 * Identifies components that can be used to format tooltip content
 * @author bibounde
 *
 */
public interface SpiderTooltipFormatter {

    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, double value);
}
