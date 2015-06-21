package com.jpmcc.stock;


public class PreferredStock extends Stock {
	
	public PreferredStock(String Symbol, double lastDividend, double parValue, double fixedDividend) {
		super();
		this.setSymbol(Symbol);
		this.setType(StockType.Preferred);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
	}
	@Override
	public void showStockInfo() {
		// TODO Auto-generated method stub
		System.out.println("Type: " + this.getType() + "; Symbol: " + this.getSymbolString() +
				"; Last Dividend: " + this.getLastDividend() +
				"; Fixed Dividend: " + this.getFixedDividend() +
				"; Par Value: " + this.getParValue());
	}
}
