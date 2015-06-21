package com.jpmcc;

import java.util.HashSet;

import com.jpmcc.handler.CalculateDividendYieldRequest;
import com.jpmcc.handler.CalculateDividendYieldResponse;
import com.jpmcc.handler.CalculateGeometricMeanRequest;
import com.jpmcc.handler.CalculateGeometricMeanResponse;
import com.jpmcc.handler.CalculatePERatioRequest;
import com.jpmcc.handler.CalculatePERatioResponse;
import com.jpmcc.handler.CalculateStockPriceRequest;
import com.jpmcc.handler.CalculateStockPriceResponse;
import com.jpmcc.handler.DataHandler;
import com.jpmcc.stock.StockSymbol;

public class Calculator {

	private StockRecords stockRecords;
	private TradeRecords tradeRecords;
	
	public void setStocks(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}
	
	public void setTrades(TradeRecords tradeRecords) {
		this.tradeRecords = tradeRecords;
	}
	
	public CalculateDividendYieldResponse getDividendYield(StockSymbol symbol, 
			double tickerPrice) {
		
		DataHandler dataHandeller = new DataHandler();
		dataHandeller.setStockRecords(stockRecords);
		if (!dataHandeller.checkStocksData(stockRecords)) 
			return null;
        
		CalculateDividendYieldRequest request = new CalculateDividendYieldRequest();
		request.setStock(dataHandeller.getStockFromStockRecords(symbol, stockRecords));
		request.setTickerPrice(tickerPrice);
		
		CalculateDividendYieldResponse result = request.runRequest();
		System.out.println(result.toString());
		return result;
	}
	
	public CalculatePERatioResponse getPERatio(StockSymbol symbol, double ticker) {
		
		DataHandler dataHandeller = new DataHandler();
		dataHandeller.setStockRecords(stockRecords);
		if (!dataHandeller.checkStocksData(stockRecords))
		    return null;

		CalculatePERatioRequest request = new CalculatePERatioRequest();
		request.setStock(dataHandeller.getStockFromStockRecords(symbol, stockRecords));
		request.setTickerPrice(ticker);
		double dividend = getDividendYield(symbol, ticker).getDividendYield();
		if (getDividendYield(symbol, ticker).getSuccess() && dividend != 0f)
			request.setDividend(dividend);
		CalculatePERatioResponse result = request.runRequest();
		System.out.println(result.toString());
		return result;
	}
	
	public CalculateStockPriceResponse getStockPrice(StockSymbol symbol, TradeRecords tradeRecords, int period) {
		
		
		DataHandler dataHandeller = new DataHandler();
		dataHandeller.setTradeRecords(tradeRecords);
		if (!dataHandeller.checkTradesData(tradeRecords))
			return null;
		
		CalculateStockPriceRequest request = new CalculateStockPriceRequest();
		request.setStock(dataHandeller.getStockFromStockRecords(symbol, stockRecords));
		request.setStockTrades(dataHandeller.getStockTradesFromTradeRecords(symbol, period));
		CalculateStockPriceResponse result = request.runRequest();
		System.out.println(result.toString());
		return result;
	}
	
	public CalculateGeometricMeanResponse getGeometricMean(TradeRecords tradeRecords, int period) {
		
		DataHandler dataHandeller = new DataHandler();
		dataHandeller.setTradeRecords(tradeRecords);
		if (!dataHandeller.checkTradesData(tradeRecords))
			return null;
		HashSet<StockSymbol> symbols = dataHandeller.getAllStockSymbolFromTradeRecords(tradeRecords);
		if (symbols.size() == 0)
			return null;
		CalculateGeometricMeanRequest request = new CalculateGeometricMeanRequest();
		request.setPeriod(period);
		for (StockSymbol symbol : symbols) {
			CalculateStockPriceResponse res = getStockPrice(symbol, tradeRecords, period);
			if (!res.getSuccess())
				throw new UnsupportedOperationException("Error occured while calculating stock price for " + symbol);
			request.addStockPrices(res.getStockPrice());
			request.addRecords(dataHandeller.getStockTradesFromTradeRecords(symbol, period).size());
			request.addStockSymbols(symbol);
		}
		CalculateGeometricMeanResponse result = request.runRequest();
		System.out.println(result.toString());
		return result;
	}

}
