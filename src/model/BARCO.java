package model;

public enum BARCO {
	
	BARCO1("/resources/pirata.png", "/resources/pirata.png"),
	BARCO2("/resources/barco2.png", "/resources/barco2VIDA.png"),
	BARCO3("/resources/barco3.png", "/resources/barco3VIDA.png");

	
	String urlBarco;
	String urlVida;
	
	private BARCO(String urlBarco, String urlVida) {
		this.urlBarco = urlBarco;
		this.urlVida = urlVida;
	}
	
	public String getUrl() {
		return this.urlBarco;
	}
	public String getUrlVida() {
		return urlVida;
	}
}