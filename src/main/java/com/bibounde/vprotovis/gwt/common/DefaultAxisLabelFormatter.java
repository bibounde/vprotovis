package com.bibounde.vprotovis.gwt.common;

public class DefaultAxisLabelFormatter implements AxisLabelFormatter {

    public String format(double labelValue) {
        return String.valueOf(labelValue);
    }

}
