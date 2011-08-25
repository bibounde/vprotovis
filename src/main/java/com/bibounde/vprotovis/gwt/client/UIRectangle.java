package com.bibounde.vprotovis.gwt.client;

public class UIRectangle {

    public double top, left, width, height;

    public UIRectangle(double left, double top, double width, double height) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }
    
    public boolean contains(double x, double y) {
        return x >= left && x <= left + width && y >= top && y <= top + height;  
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UIRectangle [left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + "]";
    }
    
    
}
