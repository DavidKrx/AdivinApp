package dad.javafx.adivin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private int min;
	private int max;
	private int random_int;

	private Button comprobarButton;
	private TextField numeroText;
	private Label instruccionesLabel;
	private Alert acierto;
	private Alert fallo;
	private Alert numeroNoValido;
	int Intentos;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Intentos = 0;
		min = 1;
		max = 100;
		random_int = (int) (Math.random() * (max - min + 1) + min);

		numeroText = new TextField();
		numeroText.setPrefColumnCount(5);
		numeroText.setPromptText("Introduce un número del 1 al 100");
		numeroText.setMaxWidth(150);
		numeroText.setAlignment(Pos.CENTER); ;

		instruccionesLabel = new Label();
		instruccionesLabel.setTranslateY(-80);
		instruccionesLabel.setText("Introduce un número del 1 al 100");

		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		comprobarButton.setDefaultButton(true);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(numeroText, comprobarButton, instruccionesLabel);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();

	}

	private void onComprobarButtonAction(ActionEvent e) {

		try {
			int numIntroducido = Integer.parseInt(numeroText.getText());
			String mayorMenor = "";

			if (numIntroducido >= min && numIntroducido <= max) {
				System.out.println(numIntroducido + "  " + random_int);
				if (random_int == numIntroducido) {
					acierto = new Alert(AlertType.INFORMATION);
					acierto.setTitle("AdivinApp");
					acierto.setHeaderText("¡Has ganado!");
					acierto.setContentText(
							"Sólo has necesitado " + Intentos + " intentos" + "\n\n" + "Vuelve a jugar y hazlo mejor.");
					acierto.showAndWait();
					random_int = (int)(Math.random() * (max - min + 1) + min);
					Intentos = 0;
				} else if (random_int != numIntroducido) {
					Intentos++;
					if (numIntroducido < random_int)
						mayorMenor = "mayor";
					else
						mayorMenor = "menor";

					fallo = new Alert(AlertType.WARNING);
					fallo.setTitle("AdivinApp");
					fallo.setHeaderText("¡Has fallado!");
					fallo.setContentText("El número a adivinar es " + mayorMenor + " que " + numIntroducido);

					fallo.showAndWait();
				}

			} else {
				Error0();
			}

		} catch (NumberFormatException error) {
			Error0();
		}
	}

	private void Error0() {
		numeroNoValido = new Alert(AlertType.ERROR);
		numeroNoValido.setTitle("AdivinApp");
		numeroNoValido.setHeaderText("Error");
		numeroNoValido.setContentText("El número introducido no es válido");
		numeroNoValido.showAndWait();
	}

}
