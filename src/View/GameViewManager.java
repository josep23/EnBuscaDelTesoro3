package View;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.BARCO;
import model.TituloSelecciondeBarcos;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView barco;
	
	private boolean Sipulsasizquierda;
	private boolean Sipulsasderecha;
	private AnimationTimer gameTimer;
	private int angle;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "/resources/ocean.png";
	
	
	private final static String DRAGONDEAGUA = "/resources/dragonwater.png";
	private final static String PIEDRA = "/resources/piedra.png";
	
	
	private ImageView[] dragondeagua;
	private ImageView[] piedra;
	Random Posicionaleatoria;
	
	
	private ImageView cofre;
	private TituloSelecciondeBarcos pointsLabel;
	private ImageView[] vidas;
	private int vidasdeljugador;
	private int puntos;
	private final static String COFREDELTESORO = "/resources/cofre_tesoro.png";

	private int Velocidad=1;

	private final static int RADIODELCOFRE = 12;
	private final static int RADIODELBARCO = 27;
	private final static int RADIODEOBSTACULOS = 20;
	
	public GameViewManager() {
		iniciarStage();
		createKeyListeners();
		Posicionaleatoria = new Random();
		
	}
	
	private void createKeyListeners() {
		
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					Sipulsasizquierda = true;
					
				} else if (event.getCode() == KeyCode.RIGHT) {
					Sipulsasderecha = true;
				}
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					Sipulsasizquierda = false;
					
				} else if (event.getCode() == KeyCode.RIGHT) {
					Sipulsasderecha = false;
					
				}
			}
		});
	}

	private void iniciarStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}

	public void crearjuego(Stage menuStage, BARCO elegirbarco) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		crearFondo();
		crearbarco(elegirbarco);
		crearelementosdeljuego(elegirbarco);
		createGameLoop();
		gameStage.show();
	}
	
	private void crearelementosdeljuego(BARCO barcoelegido) {
		vidasdeljugador = 2;
		cofre = new ImageView(COFREDELTESORO);
		nuevaposiciondelelemento(cofre);
		gamePane.getChildren().add(cofre);
		pointsLabel = new TituloSelecciondeBarcos("PUNTOS : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		vidas = new ImageView[3];
		
		for(int i = 0; i < vidas.length; i++) {
			vidas[i] = new ImageView(barcoelegido.getUrlVida());
			vidas[i].setLayoutX(455 + (i * 50));
			vidas[i].setLayoutY(80);
			gamePane.getChildren().add(vidas[i]);
			
		}

		dragondeagua = new ImageView[3];
		for(int i = 0; i < dragondeagua.length; i++) {
			dragondeagua[i] = new ImageView(DRAGONDEAGUA);
			nuevaposiciondelelemento(dragondeagua[i]);
			gamePane.getChildren().add(dragondeagua[i]);
		}
		piedra = new ImageView[3];
		for(int i = 0; i < piedra.length; i++) {
			piedra[i] = new ImageView(PIEDRA);
			nuevaposiciondelelemento(piedra[i]);
			gamePane.getChildren().add(piedra[i]);
		}
	}

	private void movimientodeloselementos() {
		cofre.setLayoutY(cofre.getLayoutY() + Velocidad);
		
		for(int i = 0; i < dragondeagua.length; i++) {
			dragondeagua[i].setLayoutY(dragondeagua[i].getLayoutY()+Velocidad);
		}
		
		for(int i = 0; i < piedra.length; i++) {
			piedra[i].setLayoutY(piedra[i].getLayoutY()+Velocidad);
		}
	}

	private void aumentar_velocidad() {
		for (int i = 0; i < dragondeagua.length; i++) {

			if (puntos > 20 ){
				Velocidad=5;
			}
			else if (puntos > 15 ){
				Velocidad=4;
			}
			else if (puntos > 10 ){
				Velocidad=3;
			}
			else if (puntos > 5 ){
				Velocidad=2;
			}
		}
	}

	private void comprobarSiElElementoEstaDetras() {
		if(cofre.getLayoutY() > 1200) {
			nuevaposiciondelelemento(cofre);
		}
		
		for(int i = 0; i< dragondeagua.length; i++) {
			if(dragondeagua[i].getLayoutY() > 900) {
				nuevaposiciondelelemento(dragondeagua[i]);
			}
		}

		for(int i = 0; i< piedra.length; i++) {
			if(piedra[i].getLayoutY() > 900) {
				nuevaposiciondelelemento(piedra[i]);
			}
		}
	}
	
	private void nuevaposiciondelelemento(ImageView image) {
		image.setLayoutX(Posicionaleatoria.nextInt(370));
		image.setLayoutY(-Posicionaleatoria.nextInt(3200)+600);
		
	}
	
	private void crearbarco(BARCO barcoelegido) {
		barco = new ImageView(barcoelegido.getUrl());
		barco.setLayoutX(GAME_WIDTH/2);
		barco.setLayoutY(GAME_HEIGHT - 200);
		gamePane.getChildren().add(barco);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				movimientodelfondo();
				movimientodeloselementos();
				aumentar_velocidad();
				comprobarSiElElementoEstaDetras();
				comprobarsichocan();
				movimientoDelBarco();
			}
			
		};
		gameTimer.start();
	}
	
	private void movimientoDelBarco() {
		
		if (Sipulsasizquierda && !Sipulsasderecha) {
			if(angle > -30) {
				angle -= 5;
			}
			barco.setRotate(angle);
			if(barco.getLayoutX() > -20) {
				barco.setLayoutX(barco.getLayoutX() - 3);
			}
		}
		
		if (Sipulsasderecha && !Sipulsasizquierda) {
			if(angle < 30) {
				angle += 5;
			}
			barco.setRotate(angle);
			if(barco.getLayoutX() < 522) {
				barco.setLayoutX(barco.getLayoutX() + 3);
			}
		}
		
		if (!Sipulsasizquierda && !Sipulsasderecha) {
			if(angle < 0) {
				angle += 5;
			} else if (angle > 0) {
				angle -= 5;
			}
			barco.setRotate(angle);
		}
		
		if (Sipulsasizquierda && Sipulsasderecha) {
			if(angle < 0) {
				angle += 5;
			} else if (angle > 0) {
				angle -= 5;
			}
			barco.setRotate(angle);
		}
	}

	private void crearFondo() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for (int i = 0 ; i < 12; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1, i% 3, i / 3 );
			GridPane.setConstraints(backgroundImage2, i% 3, i / 3 );
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
		}
		
		gridPane2.setLayoutY(- 1024);
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}
	
	private void movimientodelfondo() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
		
		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		
		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
	
	private void comprobarsichocan() {
		
		if(RADIODELBARCO + RADIODELCOFRE > calculardistancia(barco.getLayoutX() + 49, cofre.getLayoutX() + 15,
				barco.getLayoutY() + 37, cofre.getLayoutY() + 15)) {
			nuevaposiciondelelemento(cofre);
			
			puntos++;
			String textToSet = "PUNTOS : ";
			if (puntos < 10) {
				textToSet = textToSet + "0";
			}
			pointsLabel.setText(textToSet + puntos);
		}
		
		for (int i = 0; i < dragondeagua.length; i++) {
			
			if (RADIODEOBSTACULOS + RADIODELBARCO > calculardistancia(barco.getLayoutX() + 49, dragondeagua[i].getLayoutX() + 20,
					barco.getLayoutY() + 37, dragondeagua[i].getLayoutY() + 20)) {
				
				perdervida();
				nuevaposiciondelelemento(dragondeagua[i]);
			}
		}
		
		for (int i = 0; i < piedra.length; i++) {
			
			if (RADIODEOBSTACULOS + RADIODELBARCO > calculardistancia(barco.getLayoutX() + 49, piedra[i].getLayoutX() + 20,
					barco.getLayoutY() + 37, piedra[i].getLayoutY() + 20)) {
				
				perdervida();
				nuevaposiciondelelemento(piedra[i]);
			}
		}
	}

	private void perdervida() {
		
		gamePane.getChildren().remove(vidas[vidasdeljugador]);
		vidasdeljugador--;
		if(vidasdeljugador < 0) {
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
		}
	}
	
	private double calculardistancia(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}