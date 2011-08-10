package com.bibounde.vprotovis.gwt.common;

import java.util.ArrayList;
import java.util.List;

public class Range {

    private double start, stop, step;

    public Range(double start, double stop, double step) {
        super();
        this.start = start;
        this.stop = stop;
        this.step = step;
    }
    
    public String[] getRangeArrayAsString() {
        List<String> ret = new ArrayList<String>();
        for (double i = start; i < stop; i += step) {
            ret.add(String.valueOf(i));
        }
        return ret.toArray(new String[ret.size()]);
    }
    
    public Double[] getRangeArray() {
        List<Double> ret = new ArrayList<Double>();
        for (double i = start; i < stop; i += step) {
            ret.add(i);
        }
        return ret.toArray(new Double[ret.size()]);
    }

    /**
     * @return the start
     */
    public double getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(double start) {
        this.start = start;
    }

    /**
     * @return the stop
     */
    public double getStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(double stop) {
        this.stop = stop;
    }

    /**
     * @return the step
     */
    public double getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(double step) {
        this.step = step;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Range [start=" + start + ", stop=" + stop + ", step=" + step + "]";
    }
}
