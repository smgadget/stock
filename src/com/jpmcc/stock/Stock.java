package com.jpmcc.stock;


public abstract class Stock {
	private StockSymbol symbol;
	private StockType type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private double dividend;
	
	public abstract void showStockInfo();
	public StockSymbol getStockSymbol() {
		return this.symbol;
	}
	public String getSymbolString() {
		return this.symbol.toString();
	}
	public double getLastDividend() {
		return this.lastDividend;
	}
	public double getParValue() {
		return this.parValue;
	}
	public double getFixedDividend() {
		return this.fixedDividend;
	}
	public void setSymbol(String symbol) {
		this.symbol = StockSymbol.valueOf(symbol);
	}
	public void setSymbol(StockSymbol symbol) {
		this.symbol = symbol;
	}
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	public StockType getType() {
		return type;
	}
	public void setType(StockType type) {
		this.type = type;
	}
	public double getDividend() {
		return dividend;
	}
	public void setTDividend(double dividend) {
		this.dividend = dividend;
	}
}
