package com.bibounde.vprotovis.common;

/**
 * Default implementation
 * @author bibounde
 *
 */
public class DefaultAxisLabelFormatter implements AxisLabelFormatter {

    /**
     * 
     * @return String.valueOf(labelValue)
     */
    public String format(double labelValue) {
        return String.valueOf(labelValue);
    }

}
