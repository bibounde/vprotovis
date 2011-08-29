package com.bibounde.vprotovis.chart.line;

import java.io.Serializable;

import com.bibounde.vprotovis.common.Point;

/**
 * Identifies components that can be used to format line tooltip value
 * 
 * @author bibounde
 * 
 */
public interface LineTooltipFormatter extends Serializable {

    /**
     * Returns tooltip content as HTML
     * @param serieName serie name which is selected
     * @param value selected value
     * @return tooltip content as HTML
     */
    String getTooltipHTML(String serieName, Point value);
}
