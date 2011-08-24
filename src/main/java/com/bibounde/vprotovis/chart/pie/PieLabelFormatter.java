package com.bibounde.vprotovis.chart.pie;

import java.io.Serializable;

/**
 * Identifies components that can be used to format pie label value
 * 
 * @author bibounde
 * 
 */
public interface PieLabelFormatter extends Serializable {
    /**
     * Returns true is labelValue must be visible
     * @param labelValue label value
     * @return true is labelValue must be visible
     */
    boolean isVisible(double labelValue);

    /**
     * Formats a label value into a string
     * 
     * @param labelValue
     *            label value
     * @return the formatted value string
     */
    String format(double labelValue);
}
