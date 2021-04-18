package model;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class Botones extends Button{
	
	private final String TIPOFUENTE = "/resources/MisterRocky.ttf";
	private final String IMAGENBOTONPULSADO = "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button_pressed.png');";
	private final String IMAGENBOTONSINPULSAR = "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button.png');";
	
	public Botones(String text) {
		setText(text);
		fuentedelboton();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(IMAGENBOTONSINPULSAR);
		FuncionalidadDelBoton();
		
	}

	private void fuentedelboton() {
		
		setFont(Font.loadFont(getClass().getResourceAsStream(TIPOFUENTE), 23));
		
	}
	
	private void estilodelbotonpulsado() {
		setStyle(IMAGENBOTONPULSADO);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
		
	}
	
	private void estilodelbotonsinpulsar() {
		setStyle(IMAGENBOTONSINPULSAR);
		setPrefHeight(45);
		setLayoutY(getLayoutY() - 4);
		
	}
	
	private void FuncionalidadDelBoton() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					estilodelbotonpulsado();
				}
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					estilodelbotonsinpulsar();
				}
				
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}
		});
	}
}