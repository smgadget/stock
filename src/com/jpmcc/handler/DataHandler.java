package com.jpmcc.handler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.jpmcc.StockRecords;
import com.jpmcc.TradeRecords;
import com.jpmcc.stock.Stock;
import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.Trade;

public class DataHandler {
	
	private StockRecords stockRecords;
	private TradeRecords tradeRecords;
	
	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}
	
	public void setTradeRecords(TradeRecords tradeRecords) {
		this.tradeRecords = tradeRecords;
	}
	
	public boolean checkStocksData(StockRecords stockRecords) {
		
		if (stockRecords == null || stockRecords.getStocks().size() == 0) {
			throw new UnsupportedOperationException("no stock info held... please add some stock data");
		} else {
			this.stockRecords = stockRecords;
			return true;
		}
	}
	
	public boolean checkTradesData(TradeRecords tradeRecords) {
		
		if (tradeRecords == null || tradeRecords.getTrades().size() == 0) {
			throw new UnsupportedOperationException("no trade info held... please add some trades");
		} else {
			this.tradeRecords = tradeRecords;
			return true;
		}
	}

	public void showStock() {
		// TODO Auto-generated method stub
		HashSet<Stock> stocks = stockRecords.getStocks();
		if (stocks.size() > 0) {
			for (Stock stock : stocks) {
				stock.showStockInfo();
			}
			
		}
	}
	
	public Stock getStockFromStockRecords(StockSymbol symbol, StockRecords stockRecords) {
        Stock stock = null;
		for (Stock s : stockRecords.getStocks()) {
			if (s.getStockSymbol().equals(symbol)) {
				stock = s;
			    break;
			}
		}
		if (stock == null)
			throw new UnsupportedOperationException("no stock found in the record with the symbol: " + symbol);
		return stock;
	}
	
	public HashSet<Stock> getAllStockFromStockRecords(StockRecords stockRecords) {
        
		return stockRecords.getStocks();
	}
	
    public HashSet<StockSymbol> getAllStockSymbolFromStockRecords(StockRecords stockRecords) {
    	HashSet<StockSymbol> symbols = new HashSet<StockSymbol>();
    	for (Stock stock : stockRecords.getStocks()) {
    		symbols.add(stock.getStockSymbol());
    	}
		return symbols;
	}
	
	public HashSet<StockSymbol> getAllStockSymbolFromTradeRecords(TradeRecords tradeRecords) {

		return tradeRecords.getStockSymbols();
	}
	
	public Map<Integer, Trade> getStockTradesFromTradeRecords(StockSymbol symbol, int period) {
		Map<Integer, Trade> trades = new HashMap<Integer, Trade>();
		for (Trade trade : tradeRecords.getTrades().values()) {
			long minutes = TimeUnit.MILLISECONDS.toMinutes(
					Calendar.getInstance().getTime().getTime()-trade.getTimeStamp().getTime()); 
			if (minutes <= period && trade.getStockSymbol().equals(symbol)) {
				trades.put(trade.getID(), trade);
			}
		}
		return trades;
	}
	
	public Map<Integer, Trade> getAllStockTrades(TradeRecords TradeRecords) {
		return tradeRecords.getTrades();
	}
	
	public boolean containStockTrades(StockSymbol symbol, TradeRecords tradeRecords) {
		boolean contains = false;
		for (Trade trade: tradeRecords.getTrades().values()) {
			if (trade.getStockSymbol() == symbol) {
				contains = true; break;
			}
		}
		return contains;
	}

	public void setPeriod(int period) {
		// TODO Auto-generated method stub
		
	}

}
