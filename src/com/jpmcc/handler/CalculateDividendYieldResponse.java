package com.jpmcc.handler;

public class CalculateDividendYieldResponse extends Response {
    private double dividendYield;
    
	public void setDividendYield(double dividendYield) {
		// TODO Auto-generated method stub
		this.dividendYield = dividendYield;
	}

	
	public double getDividendYield() {
		return this.dividendYield;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(toString("DividendYield", success, dividendYield+""));
		return sb.toString();
	}

}
