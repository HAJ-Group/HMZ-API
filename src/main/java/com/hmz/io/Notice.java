package com.hmz.io;

class Notice {
	
	private String langue;
	
	public Notice() {
		this.langue = "Francais";
	}
	
	public Notice(String langue) {
		this.langue = langue;
	}

	public String toString() {
		return "Notice [langue=" + langue + "]";
	}

}
