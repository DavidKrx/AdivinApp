package dad.javafx.AdivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private int randomInt;
	private int min;
	private int max;
	private int Intentos;

	private Label introducirLabel;
	private TextField numeroTxF;
	private Button comprobarButt;
	private VBox vb;
	private Scene scene;
	private Alert acierto;
	private Alert fallo;
	private Alert error;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Intentos = 1;
		min = 1;
		max = 100;

		randomInt = (int) (Math.random() * (max - min + 1) + min);

		introducirLabel = new Label();
		introducirLabel.setText("Introduce un número del 1 al 100");
		introducirLabel.setAlignment(Pos.CENTER);

		numeroTxF = new TextField();
		numeroTxF.setAlignment(Pos.CENTER);
		numeroTxF.setMaxWidth(100);

		comprobarButt = new Button();
		comprobarButt.setText("Comprobar");
		comprobarButt.setAlignment(Pos.CENTER);
		comprobarButt.setOnAction(e -> onComprobarButtAction(e));

		vb = new VBox();
		vb.setSpacing(5);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(introducirLabel, numeroTxF, comprobarButt);

		scene = new Scene(vb, 320, 200);

		primaryStage.setScene(scene);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();

	}

	private void onComprobarButtAction(ActionEvent e) {
		try {
			int num = Integer.parseInt(numeroTxF.getText());
			String MayorMenor="";
			if (num >= min && num <= max) {
				if (randomInt == num) {
					acierto = new Alert(AlertType.INFORMATION);
					acierto.setTitle("AdivinApp");
					acierto.setHeaderText("¡Has ganado!");
					acierto.setContentText(
							"Sólo has necesitado " + Intentos + " intentos" + "\n\n" + "Vuelve a jugar y hazlo mejor.");
					acierto.showAndWait();
					randomInt = (int) (Math.random() * (max - min + 1) + min);
				} else if (randomInt != num) {
					Intentos++;
					if (num < randomInt) 
						MayorMenor = "mayor";
					 else 
						MayorMenor = "menor";
					
					fallo = new Alert(AlertType.WARNING);
					fallo.setTitle("AdivinApp");
					fallo.setHeaderText("¡Has fallado!");
					fallo.setContentText("El número a adivinar es " + MayorMenor + " que " + num);
					fallo.showAndWait();
					
				}

			} else {
				Error0();
			}
		} catch (Exception er) {

		}
	}

	private void Error0() {
		error = new Alert(AlertType.ERROR);
		error.setTitle("AdivinApp");
		error.setHeaderText("Error");
		error.setContentText("El número introducino no es válido");
		error.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
