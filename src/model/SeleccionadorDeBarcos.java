package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SeleccionadorDeBarcos extends VBox {

	private ImageView botoncirculo;
	private ImageView imagenBarco;
	private String botoncircularsinseleccionar = "/resources/grey_circle.png";
	private String botoncirularseleccionado = "/resources/red_choosen.png";
	private BARCO BARCO;
	private boolean seleccionado;

	public SeleccionadorDeBarcos(BARCO BARCO) {
		botoncirculo = new ImageView(botoncircularsinseleccionar);
		imagenBarco = new ImageView(BARCO.getUrl());
		this.BARCO = BARCO;
		seleccionado = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(botoncirculo);
		this.getChildren().add(imagenBarco);
		
	}
	
	public BARCO getBarco() {
		return BARCO;
	}
	
	public boolean getBotoncirularseleccionado() {
		return seleccionado;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.seleccionado = isCircleChoosen;
		String imageToSet = this.seleccionado ? botoncirularseleccionado : botoncircularsinseleccionar;
		botoncirculo.setImage(new Image(imageToSet));
	}
}