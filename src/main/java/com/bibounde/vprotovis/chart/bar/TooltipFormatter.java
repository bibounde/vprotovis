package com.bibounde.vprotovis.chart.bar;

import java.io.Serializable;

public interface TooltipFormatter extends Serializable {

    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @param groupName selected group
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, double value, String groupName);
}
