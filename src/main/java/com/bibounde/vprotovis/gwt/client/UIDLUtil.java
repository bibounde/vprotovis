package com.bibounde.vprotovis.gwt.client;


public class UIDLUtil {

    /**
     * Return string which represents an array (ex: [12, 13, 14]) to evaluate in native js
     * @param values source
     * @param asString if true add quote (ex: ['12', '13', '14'])
     * @return string which represents an array (ex: [12, 13, 14]) to evaluate in native js
     */
    public static String getJSArray(String[] values, boolean asString) {
        StringBuilder ret = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append(asString ? "'" : "").append(values[i]).append(asString ? "'" : "");
        }
        ret.append("]");
        return ret.toString();
    }
    
    /**
     * Return string which represents an array (ex: [12, 13, 14]) to evaluate in native js
     * @param values source
     * @return string which represents an array (ex: [12, 13, 14]) to evaluate in native js
     */
    public static String getJSArray(int[] values) {
        StringBuilder ret = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append(values[i]);
        }
        ret.append("]");
        return ret.toString();
    }
}
