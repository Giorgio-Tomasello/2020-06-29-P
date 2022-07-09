package it.polito.tdp.PremierLeague.model;

public class Mese {
	
	private int n ;
	private String mese;
	
	
	public Mese(int n, String mese) {
		super();
		this.n = n;
		this.mese = mese;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public String getMese() {
		return mese;
	}


	public void setMese(String mese) {
		this.mese = mese;
	}


	@Override
	public String toString() {
		return mese;
	}
	
	
	
	

}
