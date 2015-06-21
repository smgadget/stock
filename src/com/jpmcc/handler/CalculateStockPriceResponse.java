package com.jpmcc.handler;

public class CalculateStockPriceResponse extends Response {

	double stockPrice;
	public void setStockPrice(double stockPrice) {
		// TODO Auto-generated method stub
		this.stockPrice = stockPrice;
	}
	public double getStockPrice() {
		return this.stockPrice;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(toString("StockPrice", success, stockPrice+""));
		return sb.toString();
	}

}
