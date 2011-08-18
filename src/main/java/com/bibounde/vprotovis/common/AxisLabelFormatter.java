package com.bibounde.vprotovis.common;

import java.io.Serializable;

public interface AxisLabelFormatter extends Serializable{

    String format(double labelValue);
}
