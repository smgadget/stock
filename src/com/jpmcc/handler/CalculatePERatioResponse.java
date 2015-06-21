package com.jpmcc.handler;


public class CalculatePERatioResponse extends Response {

	private double PERatio;
	public void setPERatio(double PERatio) {
		// TODO Auto-generated method stub
		this.PERatio = PERatio;
	}
	public double getPERatio() {
		return this.PERatio;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(toString("PERatio", success, PERatio+""));
		return sb.toString();
	}

}
