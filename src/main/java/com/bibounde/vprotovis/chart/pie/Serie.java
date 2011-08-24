package com.bibounde.vprotovis.chart.pie;

import java.io.Serializable;

public class Serie implements Serializable {

    private String name;
    private double value;
    private boolean highlight;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
    /**
     * @return the highlight
     */
    public boolean isHighlight() {
        return highlight;
    }
    /**
     * @param highlight the highlight to set
     */
    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }
}
