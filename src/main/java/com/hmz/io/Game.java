package com.hmz.io;

import java.io.Serializable;

class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nom, style;
	private double prix;
	// WILL BE IGNORED WHILE SERIALIZING
	private transient Notice notice;
	
	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Game(String nom, String style, double prix) {
		this.nom = nom;
		this.style = style;
		this.prix = prix;
		this.notice = new Notice();
	}

	public String toString() {
		return "Game [nom=" + nom + ", style=" + style + ", prix=" + prix + "]";
	}

	public static void main(String[] args) {
		System.out.println(new Game("tomb raider", "action", 120));
	}

}
