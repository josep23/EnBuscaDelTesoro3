package View;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.MarcadorPuntos;
import model.BARCO;
import model.SeleccionadorDeBarcos;
import model.Botones;
import model.FondoDeSeleccionDeBarcos;

public class ViewManager {

	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	private FondoDeSeleccionDeBarcos seleccionadordebarcos;
	private FondoDeSeleccionDeBarcos esconderescena;
	List<Botones> menubotones;
	List<SeleccionadorDeBarcos> listadebarcos;
	private BARCO barcoelegido;

	public ViewManager() {
		menubotones = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT );
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		crearsubescena();
		crearbotones();
		Crearmenufondo();
		Titulo();
	}

	private void enseñarsubescena(FondoDeSeleccionDeBarcos subScene) {
		if (esconderescena != null) {
			esconderescena.movermenuBarcos();
		}

		subScene.movermenuBarcos();
		esconderescena = subScene;
	}

	private void crearsubescena() {
		escenaelegirbarco();
	}

	private void escenaelegirbarco() {
		seleccionadordebarcos = new FondoDeSeleccionDeBarcos();
		mainPane.getChildren().add(seleccionadordebarcos);

		MarcadorPuntos eligebarco = new MarcadorPuntos("Que Barco Quieres?");
		eligebarco.setLayoutX(110);
		eligebarco.setLayoutY(25);
		seleccionadordebarcos.getPane().getChildren().add(eligebarco);
		seleccionadordebarcos.getPane().getChildren().add(crearbarcosparaelegir());
		seleccionadordebarcos.getPane().getChildren().add(crearbotonstar());
	}

	private HBox crearbarcosparaelegir() {
		HBox box = new HBox();
		box.setSpacing(60);
		listadebarcos = new ArrayList<>();
		for (BARCO BARCO : BARCO.values()) {
			SeleccionadorDeBarcos barcoaelegir = new SeleccionadorDeBarcos(BARCO);
			listadebarcos.add(barcoaelegir);
			box.getChildren().add(barcoaelegir);
			barcoaelegir.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (SeleccionadorDeBarcos barco : listadebarcos) {
						barco.setIsCircleChoosen(false);
					}
					barcoaelegir.setIsCircleChoosen(true);
					barcoelegido = barcoaelegir.getBarco();
				}
			});
		}

		box.setLayoutX(300 - (118*2));
		box.setLayoutY(100);
		return box;
	}

	private Botones crearbotonstar() {
		Botones botonStart = new Botones("Empezar");
		botonStart.setLayoutX(350);
		botonStart.setLayoutY(300);

		botonStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (barcoelegido != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.crearjuego(mainStage, barcoelegido);;
				}
			}
		});

		return botonStart;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	private void Añadirbotones(Botones button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menubotones.size() * 100);
		menubotones.add(button);
		mainPane.getChildren().add(button);
	}

	private void crearbotones() {
		CrearbotonEmpezar();
		CrearBotonSalir();
	}

	private void CrearbotonEmpezar() {
		Botones startButton = new Botones("Empezar");
		Añadirbotones(startButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				enseñarsubescena(seleccionadordebarcos);

			}
		});
	}


	private void CrearBotonSalir() {
		Botones exitButton = new Botones("Salir");
		Añadirbotones(exitButton);

		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();

			}
		});

	}
	private void Titulo() {
		ImageView logo = new ImageView("/resources/logo_pirata.png");
		logo.setLayoutX(600);
		logo.setLayoutY(10);

		mainPane.getChildren().add(logo);
	}

	private void Crearmenufondo() {
		Image backgroundImage = new Image("/resources/menuisla.png", 768, 1024, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));

	}
}