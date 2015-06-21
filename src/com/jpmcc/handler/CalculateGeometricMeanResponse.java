package com.jpmcc.handler;

public class CalculateGeometricMeanResponse extends Response {

	private double mean;

	public void setMean(double mean) {
		// TODO Auto-generated method stub
		this.mean = mean;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(toString("GeometicMean", success, mean +""));
		return sb.toString();
	}

}
