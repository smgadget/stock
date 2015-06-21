package com.jpmcc;

import java.util.HashSet;

import com.jpmcc.stock.Stock;

public class StockRecords {
	private HashSet<Stock> stocks;
	public StockRecords() {
		setStocks(new HashSet<Stock>());
	}
	
	public void addStock(Stock stock) {
		getStocks().add(stock);
	}

	
	public HashSet<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(HashSet<Stock> stocks) {
		this.stocks = stocks;
	}
	
}
