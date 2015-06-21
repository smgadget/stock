package com.jpmcc.stock;

public enum StockSymbol {
	TEA {
		public String toString() {
			//just in case the full name is required...
			return "TEA";
		}
	},
	
	POP {
		public String toString() {
			return "POP";
		}
	},
	
	ALE {
		public String toString() {
			return "ALE";
		}
	},
	
	GIN {
		public String toString() {
			return "GIN";
		}
	},
	
	JOE {
		public String toString() {
			return "JOE";
		}
	};
}
