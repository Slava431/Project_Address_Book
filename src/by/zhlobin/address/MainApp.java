package by.zhlobin.address;

import java.io.IOException;

import by.zhlobin.address.model.Person;
import by.zhlobin.address.view.PersonEditDialogController;
import by.zhlobin.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {

		// В качестве образца добавляем некоторые данные

		personData.add(new Person("Peter", "O’Neal"));
		personData.add(new Person("Robert", "Ball"));
		personData.add(new Person("Brian", "Nichols"));
		personData.add(new Person("Stuart", "Cobb"));
		personData.add(new Person("Richard", "Cain"));
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddresBook");

		this.primaryStage.getIcons().add(new Image("file:image/iconfinder_secret_book_66344.png"));

		initRootLayout();

		showPersonOverview();

	}

	// Инициализирует корневой макет.

	public void initRootLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showPersonOverview() {
		try {

			// Загружаем сведения об адресатах.

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(personOverview);

			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showPersonEditDialog(Person person) {
		try {
			// Загружаем fxml-файл и создаём новую сцену
			// для всплывающего диалогового окна.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Создаём диалоговое окно Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Передаём адресата в контроллер.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			// Отображаем диалоговое окно и ждём, пока пользователь его не закроет
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Возвращает главную сцену.

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
