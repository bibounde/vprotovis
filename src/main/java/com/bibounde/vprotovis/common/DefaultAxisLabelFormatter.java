package com.bibounde.vprotovis.common;

import java.text.DecimalFormat;

/**
 * Default implementation
 * @author bibounde
 *
 */
public class DefaultAxisLabelFormatter implements AxisLabelFormatter {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.#"); 
    /**
     * 
     * @return String.valueOf(labelValue)
     */
    public String format(double labelValue) {
        return decimalFormat.format(labelValue);
    }

}
