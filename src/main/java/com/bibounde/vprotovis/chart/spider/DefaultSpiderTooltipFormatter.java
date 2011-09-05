package com.bibounde.vprotovis.chart.spider;

public class DefaultSpiderTooltipFormatter implements SpiderTooltipFormatter {

    @Override
    public String getTooltipHTML(String serieName, double value) {
        StringBuilder ret = new StringBuilder();
        ret.append("<b>").append(serieName).append("</b><br/>");
        ret.append(value);
        
        return ret.toString();
    }

}
