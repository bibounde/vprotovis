package com.bibounde.vprotovis.chart;

/**
 * 
 * How to interpolate between values. Linear interpolation ("linear") is the
 * default, producing a straight line between points. For piecewise constant
 * functions (i.e., step functions), either "step-before" or "step-after" can be
 * specified. To draw open uniform b-splines, specify "basis". To draw
 * cardinal splines, specify "cardinal".
 * 
 * @author bibounde
 * 
 */
public enum InterpolationMode {

    LINEAR("linear"), STEP_BEFORE("step-before"), STEP_AFTER("step-after"), BASIS("basis"), CARDINAL("cardinal");

    private final String jsValue;

    InterpolationMode(String jsValue) {
        this.jsValue = jsValue;
    }

    /**
     * @return the jsValue
     */
    public String getJsValue() {
        return jsValue;
    }
}
