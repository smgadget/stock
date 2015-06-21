package com.jpmcc;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.Trade;

public class TradeRecords {
	private Map<Integer, Trade> trades;
	
	
	public TradeRecords() {
		trades = new ConcurrentHashMap<Integer, Trade>();
	}
	
	public Map<Integer, Trade> getTrades() {
		return this.trades;
	}
	
	public HashSet<StockSymbol> getStockSymbols() {
		HashSet<StockSymbol> stockSymbols = new HashSet<StockSymbol>();
		for (Trade v :trades.values()) {
			stockSymbols.add(v.getStockSymbol());
		}
		return stockSymbols;
	}
	
	public void displayTradeRecords() {
		System.out.println("*Trade records*");
		for (Trade v :trades.values()) {
			System.out.println(v.toString());
		}
		System.out.println("*Trade record display ends*");
	}
	
}
