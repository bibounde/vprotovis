package com.bibounde.vprotovis.common;

import java.io.Serializable;

public class DefaultAxisLabelFormatter implements AxisLabelFormatter, Serializable {

    public String format(double labelValue) {
        return String.valueOf(labelValue);
    }

}
