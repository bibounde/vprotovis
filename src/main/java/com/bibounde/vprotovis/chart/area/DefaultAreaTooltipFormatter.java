package com.bibounde.vprotovis.chart.area;

import com.bibounde.vprotovis.common.Point;

public class DefaultAreaTooltipFormatter implements AreaTooltipFormatter {

    public String getTooltipHTML(String serieName, Point value) {
        StringBuilder ret = new StringBuilder();
        ret.append("<b>").append(serieName).append("</b><br/>");
        ret.append("[").append(value.getX()).append(", ").append(value.getY()).append("]");
        
        return ret.toString();
    }

}
