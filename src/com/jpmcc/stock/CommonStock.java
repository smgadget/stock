package com.jpmcc.stock;


public class CommonStock extends Stock {
	
	public CommonStock(String symbol, double lastDividend, double parValue) {
		super();
		this.setSymbol(symbol);
		this.setType(StockType.Common);
		this.setLastDividend(lastDividend);
		this.setParValue(parValue);
	}
	@Override
	public void showStockInfo() {
		// TODO Auto-generated method stub
		System.out.println("Type: " + this.getType() + "; Symbol: " + this.getSymbolString() +
				"; Last Dividend: " + this.getLastDividend() +
				"; Par Value: " + this.getParValue());
	}
}
