package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TituloSelecciondeBarcos extends Label{
	
	private final static String TIPOFUENTE = "/resources/MisterRocky.ttf";
	
	public TituloSelecciondeBarcos(String text) {
		
		setPrefWidth(130);
		setPrefHeight(50);
		BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/red_info_label.png", 130, 50, false, false),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10, 10, 10, 10));
		elegirfuente();
		setText(text);
		
		
	}
	
	private void elegirfuente() {
		setFont(Font.loadFont(getClass().getResourceAsStream(TIPOFUENTE), 15));
	}


}
