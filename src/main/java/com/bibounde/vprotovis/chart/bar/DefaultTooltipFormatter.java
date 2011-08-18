package com.bibounde.vprotovis.chart.bar;

import java.io.Serializable;

public class DefaultTooltipFormatter implements TooltipFormatter {

    public String getTooltipHTML(String serieName, double value, String groupName) {
        StringBuilder ret = new StringBuilder();
        if (!"".equals(groupName)) {
            ret.append("<b>").append(groupName).append("</b><br/>");
        }
        ret.append(serieName).append(": ").append(value);
        
        return ret.toString();
    }

}
