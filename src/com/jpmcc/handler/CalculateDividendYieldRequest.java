package com.jpmcc.handler;

import com.jpmcc.stock.Stock;
import com.jpmcc.stock.StockSymbol;

public class CalculateDividendYieldRequest extends Request{
	
	StockSymbol stockSymbol;
	String symbolString;
	double tickerPrice;
	Stock stock;
	double lastDividend;
	double fixedDividend;
	double parValue;
	
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
	
	public CalculateDividendYieldResponse runRequest() {
		CalculateDividendYieldResponse calculateDividendYieldResult = new CalculateDividendYieldResponse();
		
		if (stock == null)
			throw new UnsupportedOperationException (
					"no information held for the subject stock, please check the symbol or add the stock info");
		if (tickerPrice <= 0 || tickerPrice > Double.MAX_VALUE)
			throw new ArithmeticException ("Ticker price should be a positive value and smaller than " + Double.MAX_VALUE);
		setStockSymbol(stock.getStockSymbol());
		setSymbolString(stock.getSymbolString());
		double dividendYield = 0.0d;
		switch (stock.getType()) {
		case Common:
			setLastDividend(stock.getLastDividend());
			dividendYield = lastDividend/tickerPrice;
			calculateDividendYieldResult.setDividendYield(dividendYield);
			calculateDividendYieldResult.setSuccess(true);
			break;
		case Preferred:
			setFixedDividend(stock.getFixedDividend());
			setParValue(stock.getParValue());
			dividendYield = fixedDividend*parValue/tickerPrice;
			calculateDividendYieldResult.setDividendYield(dividendYield);
			calculateDividendYieldResult.setSuccess(true);
			break;
		}
		calculateDividendYieldResult.setRequest(this);
		return calculateDividendYieldResult;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{Calculate DividendYield Request->");
		if (getSymbolString() != null) sb.append(" Stock Symbol: " + getSymbolString() + ",");
		if (getTickerPrice() != 0.0d) sb.append(" Ticker Price: " + getTickerPrice() + ",");
		if (getLastDividend() != 0.0d) sb.append(" Last Dividend: " + getLastDividend() + ",");
		if (getFixedDividend() != 0.0d) sb.append(" Fixed Dividend: " + getFixedDividend() + ",");
		if (getParValue() != 0.0d) sb.append(" Par Value: " + getParValue() + ",");
		sb.append("}");
		return sb.toString();
	}
	
}
