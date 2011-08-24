package com.bibounde.vprotovis.chart.pie;

public class DefaultPieLabelFormatter implements PieLabelFormatter{

    public boolean isVisible(double labelValue) {
        return true;
    }

    public String format(double labelValue) {
        return String.valueOf(labelValue);
    }

}
