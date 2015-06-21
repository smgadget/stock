package com.jpmcc;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jpmcc.stock.CommonStock;
import com.jpmcc.stock.PreferredStock;
import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.TradeType;

public class CaculaterTest {
    private StockRecords stocks;
    private Calculator calculater;
    TradeGenerator tg = new TradeGenerator();
	@Before
	public void setup() {
		
		stocks = new StockRecords();
		stocks.addStock(new PreferredStock("GIN", 8d, 100d, 0.02d));
		stocks.addStock(new CommonStock("TEA", 0d, 100d));
		stocks.addStock(new CommonStock("POP", 8d, 100d));
		stocks.addStock(new CommonStock("ALE", 23d, 60d));
		stocks.addStock(new CommonStock("JOE", 13d, 250d));
		
		tg.generateRandomTrades(StockSymbol.ALE, TradeType.BUY, 50, 10000, 15, 30);
		tg.generateRandomTrades(StockSymbol.JOE, TradeType.SELL, 20, 20000, 60, 50);
		tg.generateRandomTrades(StockSymbol.POP, TradeType.BUY, 10, 30000, 70, 150);
		//tg.getTradeRecords().displayTradeRecords();
		
		calculater = new Calculator();
		calculater.setStocks(stocks);
		calculater.setTrades(tg.getTradeRecords());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateDividendYieldTestPOP() {
		double ticker = 12d;
		assertEquals(8/ticker, calculater.getDividendYield(StockSymbol.POP, ticker).getDividendYield());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateDividendYieldTestGIN() {
		double ticker = 1d;
		assertEquals((0.02d*100.0d/ticker), calculater.getDividendYield(StockSymbol.GIN, ticker).getDividendYield());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculatePERatioTestALE() {
		double ticker = 21.4d;
		assertEquals(ticker/(23f/ticker), calculater.getPERatio(StockSymbol.ALE, ticker).getPERatio());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculatePERatioTestJOE() {
		double ticker = 33.2d;
		assertEquals((double)ticker/(double)(13/ticker), calculater.getPERatio(StockSymbol.JOE, ticker).getPERatio());
	}
	
	@Test
	public void tradeTestALE() {		
		calculater.getStockPrice(StockSymbol.ALE, tg.getTradeRecords(), 15);
	}
	
	@Test
	public void tradeTestJOE() {
		calculater.getStockPrice(StockSymbol.JOE, tg.getTradeRecords(), 15);
	}
	
	@Test
	public void tradeTestPOP() {
		calculater.getStockPrice(StockSymbol.POP, tg.getTradeRecords(), 15);
	}

	@Test
	public void getmetricTest() {
		calculater.getGeometricMean(tg.getTradeRecords(), 15);
	}
}
