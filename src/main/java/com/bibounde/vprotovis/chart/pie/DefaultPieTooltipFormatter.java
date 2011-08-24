package com.bibounde.vprotovis.chart.pie;

public class DefaultPieTooltipFormatter implements PieTooltipFormatter {

    public boolean isVisible(String serieName, double value) {
        return true;
    }

    public String getTooltipHTML(String serieName, double value) {
        StringBuilder ret = new StringBuilder();
        ret.append("<b>").append(serieName).append("</b><br/>");
        ret.append(value);
        
        return ret.toString();
    }

}
