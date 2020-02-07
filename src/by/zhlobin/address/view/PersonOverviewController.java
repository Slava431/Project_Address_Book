package by.zhlobin.address.view;

import by.zhlobin.address.MainApp;
import by.zhlobin.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;

	private MainApp mainApp;

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {

		// Инициализация таблицы адресатов с двумя столбцами.

		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

		showPersonDetails(null);

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldVale, newVale) -> showPersonDetails(newVale));
	}

	// Вызывается главным приложением, которое даёт на себя ссылку

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(mainApp.getPersonData());
	}

	// Показывает допонительную информцию об адресатах.

	private void showPersonDetails(Person person) {

		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			cityLabel.setText(person.getCity());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			cityLabel.setText("");
			postalCodeLabel.setText("");
		}
	}

	@FXML
	private void deletePeson() {

		int index = personTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			personTable.getItems().remove(index);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");
			
			alert.showAndWait();
		}
	}

	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		
		}
	}

	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}
		}else {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");
			
			alert.showAndWait();
		}
	}
}
