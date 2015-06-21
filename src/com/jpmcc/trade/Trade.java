package com.jpmcc.trade;

import java.util.Calendar;
import java.util.Date;

import com.jpmcc.stock.StockSymbol;

public class Trade {
	
	private int id;
	private TradeType tradeType;
	private Date timeStamp = Calendar.getInstance().getTime();
	private int quantityOfShares;
	private double price;
	private StockSymbol stockSymbol;
	
	public Trade(StockSymbol stockSymbol, TradeType tradeType, double price, int quantityOfShares, int period) {
		setID();
		setStockSymbol(stockSymbol);
		setTradeType(tradeType);
		setPrice(price);
		setQuantityOfShares(quantityOfShares);
		setTimeStamp(period);
	}
	public void displayInfo() {
		System.out.println(Calendar.getInstance().getTime() + "" + this.hashCode());		
	}
	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public StockSymbol getStockSymbol() {
		return this.stockSymbol;
	}
	public void setID() {
		this.id = this.hashCode();
	}
	public int getID() {
		return this.hashCode();
	}
	public void setTradeTypeWithString(String typeString) {
		if (tradeType != null)
			if (typeString.toLowerCase().contains("b")) {
				this.tradeType = TradeType.BUY;
			} else {
				this.tradeType = TradeType.SELL;
			}
	}
	public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
	}
	public TradeType getTradeType() {
		return this.tradeType;
	}
	
	public void setTimeStamp (int period) {
		Calendar c = Calendar.getInstance();
		
		c.add(Calendar.MINUTE, -period);
		this.timeStamp = c.getTime();
	}
	
	public Date getTimeStamp() {
		return this.timeStamp;
	}
	
	public void setQuantityOfShares(int quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	public int getQuantityOfShares() {
		return this.quantityOfShares;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return this.price;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{Trade:");
		sb.append(" ID: " + getID() + ",");
		if (getTimeStamp() != null) sb.append(" Time: " + getTimeStamp() + ",");
		if (getStockSymbol() != null) sb.append(" Stock: " + getStockSymbol() + ",");
		if (getTradeType() != null) sb.append(" Type: " + getTradeType() + ",");
		if (getPrice() != 0f) sb.append(" Price: " + getPrice() + ",");
		if (getQuantityOfShares() != 0f) sb.append(" Quantity: " + getQuantityOfShares() + ",");
		sb.append("}");
		return sb.toString();
	}

}
