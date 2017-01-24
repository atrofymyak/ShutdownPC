package com.gui;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainApp extends Application {

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

		Scene scene = new Scene(root, 400, 400);

		root.setStyle("-fx-background-color: #f0f7fc;");

		ListView<String> hoursView = (ListView<String>) scene.lookup("#hoursView");

		List<String> hours = new ArrayList<String>();
		for (int i = 1; i < 10; i++) {
			hours.add("" + i);
		}
		hoursView.setItems(new ObservableListWrapper<String>(hours));

		ListView<String> minutesView = (ListView<String>) scene.lookup("#minutesView");
		List<String> minutes = new ArrayList<String>();
		for (int i = 5; i < 61; i+=5) {
			minutes.add("" + i);
		}
		minutesView.setItems(new ObservableListWrapper<String>(minutes));

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Shutdown PC");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
