package com.bibounde.vprotovis.chart.line;

import com.bibounde.vprotovis.common.Point;

public class DefaultLineTooltipFormatter implements LineTooltipFormatter {

    public String getTooltipHTML(String serieName, Point value) {
        StringBuilder ret = new StringBuilder();
        ret.append("<b>").append(serieName).append("</b><br/>");
        ret.append("[").append(value.getX()).append(", ").append(value.getY()).append("]");
        
        return ret.toString();
    }

}
