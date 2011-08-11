package com.bibounde.vprotovis.chart.bar;

public interface TooltipFormatter {

    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @param groupName selected group
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, double value, String groupName);
}
