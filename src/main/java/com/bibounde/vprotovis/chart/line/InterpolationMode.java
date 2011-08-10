package com.bibounde.vprotovis.chart.line;

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
