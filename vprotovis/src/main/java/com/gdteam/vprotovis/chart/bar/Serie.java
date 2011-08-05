package com.gdteam.vprotovis.chart.bar;

import java.io.Serializable;

public class Serie implements Serializable{

	private String name;
	private double[] values;
	
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
	public double[] getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(double[] values) {
		this.values = values;
	}
}
