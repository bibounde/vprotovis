package com.bibounde.vprotovis.common;

import java.io.Serializable;

public class Padding implements Serializable {

    private double top, left, right, bottom;

    public Padding(double top, double left, double right, double bottom) {
        super();
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    /**
     * @return the top
     */
    public double getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(double top) {
        this.top = top;
    }

    /**
     * @return the left
     */
    public double getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(double left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public double getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(double right) {
        this.right = right;
    }

    /**
     * @return the bottom
     */
    public double getBottom() {
        return bottom;
    }

    /**
     * @param bottom the bottom to set
     */
    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Padding [top=" + top + ", left=" + left + ", right=" + right + ", bottom=" + bottom + "]";
    }
}
