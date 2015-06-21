package com.jpmcc.handler;

import com.jpmcc.stock.Stock;
import com.jpmcc.stock.StockSymbol;

public class CalculatePERatioRequest extends Request {

	StockSymbol stockSymbol;
	String symbolString;
	double tickerPrice;
	Stock stock;
	double lastDividend;
	double fixedDividend;
	double parValue;
	double dividend;
	
	public void setSymbolString(String symbolString) {
		this.symbolString = symbolString;
	}
	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public void setTickerPrice(double tickerPrice) {
		this.tickerPrice = tickerPrice;
	}
	public String getSymbolString() {
		return this.symbolString;
	}
	public StockSymbol getStockSymbol() {
		return this.stockSymbol;
	}
	public double getTickerPrice() {
		return this.tickerPrice;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Stock getStock() {
		return this.stock;
	}
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	public double getLastDividend() {
		return this.lastDividend;
	}
	public double getFixedDividend() {
		return this.fixedDividend;
	}
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	public double getParValue() {
		return this.parValue;
	}
	public void setDividend(double dividend) {
		this.dividend = dividend;
	}
	public double getDividend() {
		return this.dividend;
	}
	
	public CalculatePERatioResponse runRequest() {
		CalculatePERatioResponse result = new CalculatePERatioResponse();
		
		if (stock == null)
			throw new UnsupportedOperationException (
					"no information held for the subject stock, please check the symbol or add the stock info");
		if (dividend <= 0)
			throw new ArithmeticException("Dividend should be a positive value");
		result.setPERatio(tickerPrice/dividend);
		result.setSuccess(true);
		result.setRequest(this);
		return result;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{Calculate PERatio Request->");
		if (getSymbolString() != null) sb.append(" Stock Symbol: " + getSymbolString() + ",");
		if (getTickerPrice() != 0.0d) sb.append(" Ticker Price: " + getTickerPrice() + ",");
		if (getLastDividend() != 0.0d) sb.append(" Last Dividend: " + getLastDividend() + ",");
		if (getFixedDividend() != 0.0d) sb.append(" Fixed Dividend: " + getFixedDividend() + ",");
		if (getParValue() != 0.0d) sb.append(" Par Value: " + getParValue() + ",");
		sb.append("}");
		return sb.toString();
	}
}
