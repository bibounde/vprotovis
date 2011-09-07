package com.bibounde.vprotovis.chart.spider;

import java.text.DecimalFormat;

public class DefaultSpiderTooltipFormatter implements SpiderTooltipFormatter {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.#");
    
    @Override
    public String getTooltipHTML(String axisName, String serieName, double value) {
        StringBuilder ret = new StringBuilder();
        ret.append("<b>").append(axisName).append("</b><br/>");
        ret.append(serieName).append(": ").append(decimalFormat.format(value));
        
        return ret.toString();
    }

}
