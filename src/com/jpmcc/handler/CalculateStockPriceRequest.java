package com.jpmcc.handler;

import java.util.Map;

import com.jpmcc.TradeRecords;
import com.jpmcc.stock.Stock;
import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.Trade;
import com.jpmcc.trade.TradeType;

public class CalculateStockPriceRequest extends Request {
	private Map<Integer, Trade> trades;
	private StockSymbol stockSymbol;
	private String symbolString;
	private Stock stock;
	private TradeRecords tradeRecords;
	private TradeType tradeType;
	
	public void setSymbolString(String symbolString) {
		this.symbolString = symbolString;
	}
	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getSymbolString() {
		return this.symbolString;
	}
	public StockSymbol getStockSymbol() {
		return this.stockSymbol;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
		this.setStockSymbol(stock.getStockSymbol());
	}
	public Stock getStock() {
		return this.stock;
	}
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	public TradeType getTradeType() {
		return this.tradeType;
	}
	public void setStockTrades(Map<Integer, Trade> trades) {
		// TODO Auto-generated method stub
		this.trades = trades;
	}
	public TradeRecords getTradeRecords() {
		// TODO Auto-generated method stub
		return this.tradeRecords;
	}
	public CalculateStockPriceResponse runRequest() {
		// TODO Auto-generated method stub
		if (trades.size() == 0)
			throw new UnsupportedOperationException("No trades info found for the stock: " + getStockSymbol());
		CalculateStockPriceResponse result = new CalculateStockPriceResponse();
		int quantity = 0; double totalPrice = 0;
		for (Trade trade : trades.values()) {
			if (stockSymbol == null)
				stockSymbol = trade.getStockSymbol();
			if (tradeType == null)
				tradeType = trade.getTradeType();
			quantity+=trade.getQuantityOfShares();
			totalPrice+=trade.getPrice()*trade.getQuantityOfShares();
		}
		double stockPrice = totalPrice/quantity;
		result.setSuccess(true);
		result.setStockPrice(stockPrice);
		result.setRequest(this);
		return result;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{Calculate StockPrice Request->");
		if (getStockSymbol() != null) sb.append(" Stock Symbol: " + getStockSymbol() + ",");
		if (getTradeType() != null) sb.append(" Trade Type: " + getTradeType() + ",");
	    sb.append(" No of Trade Records: " + trades.size() + ",");
		sb.append("}");
		return sb.toString();
	}
}
