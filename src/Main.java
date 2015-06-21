
import java.util.Map;
import java.util.Scanner;

import com.jpmcc.Calculator;
import com.jpmcc.StockRecords;
import com.jpmcc.TradeGenerator;
import com.jpmcc.TradeRecords;
import com.jpmcc.handler.CalculateDividendYieldResponse;
import com.jpmcc.handler.CalculateGeometricMeanResponse;
import com.jpmcc.handler.CalculatePERatioResponse;
import com.jpmcc.handler.CalculateStockPriceResponse;
import com.jpmcc.handler.DataHandler;
import com.jpmcc.stock.CommonStock;
import com.jpmcc.stock.PreferredStock;
import com.jpmcc.stock.Stock;
import com.jpmcc.stock.StockSymbol;
import com.jpmcc.trade.Trade;
import com.jpmcc.trade.TradeType;


public class Main {
	
	static Scanner s = new Scanner(System.in);
	static StockRecords stocks;
	static TradeRecords trades;
    static Calculator calculater;
	static TradeGenerator tg = new TradeGenerator();
    public static void main (String[] args) {
	   	   
	   stocks = new StockRecords();
	   stocks.addStock(new PreferredStock("GIN", 8d, 100d, 0.02d));
	   stocks.addStock(new CommonStock("TEA", 0d, 100d));
	   stocks.addStock(new CommonStock("POP", 8d, 100d));
	   stocks.addStock(new CommonStock("ALE", 23d, 60d));
	   stocks.addStock(new CommonStock("JOE", 13d, 250d));
	   calculater = new Calculator();
	   calculater.setStocks(stocks);
	  
	   tg.generateRandomTrades(StockSymbol.ALE, TradeType.BUY, 50, 10000, 15, 30);
	   tg.generateRandomTrades(StockSymbol.JOE, TradeType.SELL, 20, 20000, 160, 50);
	   tg.generateRandomTrades(StockSymbol.POP, TradeType.BUY, 10, 30000, 90, 20);
	   tg.generateRandomTrades(StockSymbol.TEA, TradeType.SELL, 40, 50000, 170, 20);
	   tg.generateRandomTrades(StockSymbol.GIN, TradeType.BUY, 60, 80000, 120, 50);
	   //tg.getTradeRecords().displayTradeRecords();
	   trades = tg.getTradeRecords();
	   calculater.setTrades(trades);
	   
	   mainMenu(0);
		
    }
    
    static void mainMenu(int selection) {

 	  switch (oneToFive(selection)) {
	   case 1:
		   selection1();
	   case 2:
		   selection2();
	   case 3:
		   selection3();
	   case 4:
		   selection4();
	   case 5:
		   selection5();
	   case 6:
		   System.out.println("***bye***");
		   System.exit(0);
	   }
 	    
    }
    
    static void selection1() {
    	System.out.println("* Selection 1 *");
    	System.out.println("enter a stock symbol for dividend yield");
    	
    	DataHandler dh = new DataHandler();//using handler 
    	dh.setStockRecords(stocks);
    	Stock stock = null;
    	String entry = s.next();
    	
    	while(stock == null) {//get the stock
    		
    		try {
    			stock = dh.getStockFromStockRecords(StockSymbol.valueOf(entry.toUpperCase()), stocks);
    		} catch (IllegalArgumentException e) {
    			System.out.println("please enter from " + dh.getAllStockSymbolFromStockRecords(stocks));
    			entry = s.next();
    		}
    		
    	}
    	System.out.println(entry + " chosen, now enter a ticker price");
    	
    	double ticker = 0;
    	CalculateDividendYieldResponse resp = null;//initiate calculation request
    	while (resp == null) {
    		while (!s.hasNextFloat()) {
        		System.out.println("valid ticker price, please");
        		s.nextLine();
        	}
        	ticker = s.nextDouble();        	
        	try {
        		resp = calculater.getDividendYield(stock.getStockSymbol(), ticker);//call calculator
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		ticker = s.nextFloat();
        	}
    	}
    	
    	mainMenu(oneToFive(0));
    }
    
    static void selection2() {
    	System.out.println("* Selection 2 *");
    	System.out.println("enter a stock symbol for PE ratio");
    	
    	DataHandler dh = new DataHandler();
    	dh.setStockRecords(stocks);//set handler stocks
    	Stock stock = null;
    	String entry = s.next();
    	while(stock == null) {// get the stock input   		

    		try {
    			stock = dh.getStockFromStockRecords(StockSymbol.valueOf(entry.toUpperCase()), stocks);
    		} catch (IllegalArgumentException e) {
    			System.out.println("please enter from " + dh.getAllStockSymbolFromStockRecords(stocks));entry = s.next();
    		}
    	}
    	System.out.println(entry + " chosen, now enter a ticker price");
    	
    	double ticker = 0;
    	CalculatePERatioResponse resp = null; // initiate calculation request
    	while (resp == null) {
    		while (!s.hasNextDouble()) {
        		System.out.println("valid ticker price, please");
        		s.nextLine();
        	}
        	ticker = s.nextFloat();        	
        	try {
        		resp = calculater.getPERatio(stock.getStockSymbol(), ticker);// call calculator
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		selection2();
        	}
    	}
    	mainMenu(oneToFive(0));
    }
    
    static void selection3() {
    	System.out.println("* Selection 3 *");
    	System.out.println("enter a stock symbol for trade records");
    	
    	DataHandler dh = new DataHandler();
    	dh.setTradeRecords(trades);//set handler trades
    	Map<Integer, Trade> stockTrade = null;
    	String entry = s.next(), symbol = "";
    	while(stockTrade == null) {
    		
    		try {//check for trades
    			stockTrade = dh.getStockTradesFromTradeRecords(StockSymbol.valueOf(entry.toUpperCase()), 1);
    			symbol = entry.toUpperCase();
    		} catch (IllegalArgumentException e) {
    			System.out.println("please enter from " + dh.getAllStockSymbolFromTradeRecords(trades));
    			entry = s.next();
    		}
    	}
    	System.out.println(entry + " chosen, now enter a period back until now");
    	
    	int period = 0; stockTrade = null;
    	while (stockTrade == null) {
    		while (!s.hasNextInt()) {//get stock input
        		System.out.println("period in int please");
        		s.nextLine();
        	}
        	period = s.nextInt();        	
        	try {
        		stockTrade = dh.getStockTradesFromTradeRecords(StockSymbol.valueOf(entry.toUpperCase()), period);//get filtered trades
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		period = s.nextInt();
        	}
    	}
    	System.out.println("Showing the last " + period + " minutes of trdes of " + symbol);
    	if (stockTrade.values().size() > 0) {
    		for (Trade trade : stockTrade.values()) {
        		System.out.println(trade);
        	}
    	} else {
    		System.out.println("no trades found for stock, please try again");
    	}
    	
    	
    	mainMenu(oneToFive(0));
    }
    
    static void selection4() {
    	System.out.println("* Selection 4 *");
    	System.out.println("enter a stock symbol for trade records");
    	
    	DataHandler dh = new DataHandler();
    	dh.setTradeRecords(trades);//set trades
    	Map<Integer, Trade> stockTrade = null;
    	String entry = s.next();
    	StockSymbol symbol = null;
    	while(stockTrade == null) {//get stock input    		
    		try {
    			stockTrade = dh.getStockTradesFromTradeRecords(StockSymbol.valueOf(entry.toUpperCase()), 1);
    			symbol = StockSymbol.valueOf(entry.toUpperCase());
    		} catch (IllegalArgumentException e) {
    			System.out.println("please enter from " + dh.getAllStockSymbolFromTradeRecords(trades));
    			entry = s.next();
    		}
    	}
    	System.out.println(entry + " chosen, now enter a period back until now");
    	
    	int period = 0; stockTrade = null;
    	while (stockTrade == null) {
    		while (!s.hasNextInt()) {//get period input
        		System.out.println("period in int please");
        		s.nextLine();
        	}
        	period = s.nextInt();        	
        	try {
        		stockTrade = dh.getStockTradesFromTradeRecords(symbol, period);
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		period = s.nextInt();
        	}
    	}
    	
    	CalculateStockPriceResponse resp = null;//initiate calculation request
    	
    	while (resp == null) {
    		   	
        	try {
        		resp = calculater.getStockPrice(symbol, trades, period);//call calculator
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		period = s.nextInt();
        	}
    	}
    	mainMenu(oneToFive(0));
    }
    
    static void selection5() {
    	
    	System.out.println("* Selection 5 *");
    	System.out.println("now enter a period back until now");
    	int period = 0; 
    	while (!s.hasNextFloat()) {// get period input
    		System.out.println("period in int please");
    		s.nextLine();
    	}
    	period = s.nextInt(); 
    	DataHandler dh = new DataHandler();
    	dh.setTradeRecords(trades);
    	
    	CalculateGeometricMeanResponse resp = null;//initiate calculation request

    	while (resp == null) {
    		   	
        	try {
        		resp = calculater.getGeometricMean(trades, period);//call calculator
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		period = s.nextInt();
        	}
    	}
    	mainMenu(oneToFive(0));
    }
    
    static int oneToFive (int selection) {
       System.out.println("*** Main menu***");
       System.out.println("please selection from the following functions");
 	   System.out.println("1. calculate dividend for a stock");
 	   System.out.println("2. calculate PE ration for a stock");
 	   System.out.println("3. display the latest trade for a stock");
 	   System.out.println("4. calculate stock price based on trades");
 	   System.out.println("5. calculate geometric mean");
 	   System.out.println("6. exit");
 	   System.out.println("enter a selection from 1 to 6");
    	while(selection <= 0 || selection >= 7) {
  		   while(!s.hasNextInt()) {
  			   System.out.println("int, please");
  			   s.nextLine();
  		   }
  		   selection = s.nextInt();		   
  		   System.out.println((selection > 0 && selection < 7) ? selection + " selected" : "1-6, please");
  	   }
  	   return selection;
    }
}
