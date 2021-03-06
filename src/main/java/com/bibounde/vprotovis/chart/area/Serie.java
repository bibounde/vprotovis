package com.bibounde.vprotovis.chart.area;

import java.io.Serializable;

import com.bibounde.vprotovis.common.Point;

/**
 * Data displayed in a line chart
 * @author bibounde
 *
 */
public class Serie implements Serializable {

	private String name;
	private Point[] values;
	
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
	 * @return the values
	 */
	public Point[] getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(Point[] values) {
		this.values = values;
	}
}
