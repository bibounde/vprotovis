package com.bibounde.vprotovis.common;

import java.io.Serializable;

/**
 * Identifies components that can be used to format axis label value
 * @author bibounde
 *
 */
public interface AxisLabelFormatter extends Serializable{

    /**
     * Formats a label value into a string
     * @param labelValue label value
     * @return the formatted value string
     */
    String format(double labelValue);
}
