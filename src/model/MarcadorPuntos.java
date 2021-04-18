package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class MarcadorPuntos extends Label{
	
	public final static String TIPOFUENTE = "/resources/MisterRocky.ttf";
	
	public final static String IMAGENDEFONDO = "/resources/red_small_panel.png";

	public MarcadorPuntos(String text) {
		
		setPrefWidth(380);
		setPrefHeight(49);
		setText(text);
		setWrapText(true);
		elegirtipodeFuente();
		setAlignment(Pos.CENTER);
		
		BackgroundImage backgroundImage = new BackgroundImage(new Image(IMAGENDEFONDO, 380, 49, false, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(backgroundImage));
	}
	
	private void elegirtipodeFuente() {
		
		setFont(Font.loadFont(getClass().getResourceAsStream(TIPOFUENTE), 23));
		
	}
}