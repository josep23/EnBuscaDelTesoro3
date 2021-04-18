package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class FondoDeSeleccionDeBarcos extends SubScene{
	
	private final static String TIPOFUENTE = "/resources/MisterRocky.ttf";
	private final static String IMAGENDEFONDO = "/resources/red_panel.png";
	private  boolean Seesconde;

	public FondoDeSeleccionDeBarcos() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		
		BackgroundImage image = new BackgroundImage(new Image(IMAGENDEFONDO, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		Seesconde = true ;
		
		setLayoutX(1024);
		setLayoutY(180);
		
	}
	
	public void movermenuBarcos() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		if (Seesconde) {
			
			transition.setToX(-676);
			Seesconde = false;
			
		} else {
			
			transition.setToX(0);
			Seesconde = true ;
		}

		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}