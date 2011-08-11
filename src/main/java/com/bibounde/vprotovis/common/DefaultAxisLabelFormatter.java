package com.bibounde.vprotovis.common;

public class DefaultAxisLabelFormatter implements AxisLabelFormatter {

    public String format(double labelValue) {
        return String.valueOf(labelValue);
    }

}
