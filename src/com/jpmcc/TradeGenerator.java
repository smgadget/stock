package com.jpmcc;

import java.util.Random;

import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.Trade;
import com.jpmcc.trade.TradeType;

public class TradeGenerator {
	private TradeRecords records;
	public TradeGenerator() {
        records = new TradeRecords();
	}
	
	public void generateRandomTrades(StockSymbol symbol, TradeType type, float price, int quantity, int NoOfTrades, int periodInMinutes) {
		for(int i = 0; i < NoOfTrades; i++) {
			Random rPrice = new Random();
			Random rQuantity = new Random();
			Random rPeriod = new Random();
			Trade trade = new Trade(symbol, type, rPrice.nextFloat()*(price)+1, rQuantity.nextInt(quantity)+1, rPeriod.nextInt(periodInMinutes)+1);			
			records.getTrades().put(trade.getID(), trade);
		}
	}
	
	public TradeRecords getTradeRecords() {
		return records;
	}

}
