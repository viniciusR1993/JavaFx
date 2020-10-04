package gui.util;

import javafx.scene.control.TextField;

//C�digo extraido do material udemy
public class Constraints {
	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {	//Verifica se o numero � nulo e se � um numero integer (via Regex)
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldMaxLength(TextField txt, int max) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {	//Verifica se o numero � nulo e se � um numero double (via Regex)
				txt.setText(oldValue);
			}
		});
	}
}