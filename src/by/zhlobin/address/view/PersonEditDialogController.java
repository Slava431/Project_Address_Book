package by.zhlobin.address.view;

import com.jfoenix.controls.JFXTextField;

import by.zhlobin.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PersonEditDialogController {
	@FXML
	private JFXTextField firstNameField;
	@FXML
	private JFXTextField lastNameField;
	@FXML
	private JFXTextField streetField;
	@FXML
	private JFXTextField postalCodeField;
	@FXML
	private JFXTextField cityField;

	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Person person) {
		this.person = person;

		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
	}

	// Возврощает true, если пользователь кликнул OK, в другом случае false.

	public boolean isOkClicked() {
		return okClicked;
	}

	// Вызывается, когда пользователь кликнул по кнопке Ok.

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	// Вызывается, когда пользователь кликнул по кнопке Cancel.

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	//Проверяет ввод пользователя
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {

			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
