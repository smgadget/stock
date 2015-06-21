package com.jpmcc.handler;

import java.util.HashSet;
import com.jpmcc.stock.StockSymbol;

public class CalculateGeometricMeanRequest extends Request {
	private double total = 1f;
	private HashSet<StockSymbol> stockSymbols = new HashSet<StockSymbol>();
	private int recordCount = 0;
	private int period;
	public void addStockPrices(double stockPrice) {
		// TODO Auto-generated method stub
		total=total*stockPrice;
	}
	
	public CalculateGeometricMeanResponse runRequest() {
		// TODO Auto-generated method stub
		double mean = (double) StrictMath.pow(total, 1f/stockSymbols.size());
		CalculateGeometricMeanResponse result = new CalculateGeometricMeanResponse();
		result.setMean(mean);
		result.setSuccess(true);
		result.setRequest(this);
		return result;
	}

	public void addStockSymbols(StockSymbol symbol) {
		// TODO Auto-generated method stub
		this.stockSymbols.add(symbol);
	}

	public void setPeriod(int period) {
		// TODO Auto-generated method stub
		this.period = period;
	}

	public void addRecords(int size) {
		// TODO Auto-generated method stub
		recordCount+=size;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{Calculate GeometricMean Request->");
		if (stockSymbols.size() > 0) sb.append(" Stocks: " + stockSymbols + ",");
		if (this.recordCount > 0) sb.append(" No of records: " + recordCount + ",");
		if (period > 0) sb.append(" for the last " + period + " minutes,");
		sb.append("}");
		return sb.toString();
	}

}
