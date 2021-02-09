package com.stoldo.work_health;


import com.stoldo.work_health.gui.AbstractController;
import com.stoldo.work_health.gui.pages.main.MainController;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationStarter extends Application {
	
	public static void main(String[] args) {
		Application.launch(ApplicationStarter.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractController mainController = new MainController();
		mainController.load(primaryStage);
		Parent mainParent = mainController.getParent();
		Scene mainScene = new Scene(mainParent, 1102, 704);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("Application Title");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}
