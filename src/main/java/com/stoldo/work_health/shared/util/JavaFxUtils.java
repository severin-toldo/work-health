package com.stoldo.work_health.shared.util;

import java.util.List;
import com.stoldo.work_health.gui.AbstractController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFxUtils {
	
	@SuppressWarnings("unchecked")
	public static <T extends Parent> T load(AbstractController controller) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(controller.getClass().getResource(controller.getFxmlFileName()));
		fxmlLoader.setController(controller);
		
		return (T) fxmlLoader.load();
	}
	
	public static Stage openSubWindow(AbstractController c, double width, double height, String title) {
		Stage subStage = new Stage();
		c.load(subStage);
		Scene subScene = new Scene(c.getParent(), width, height);
		subStage.setScene(subScene);
		subStage.setTitle(title);
		subStage.initModality(Modality.APPLICATION_MODAL);
		subStage.show();
		
		return subStage;
	}
	
	public static <T> ObservableList<T> asObservableList(List<T> list) {
		ObservableList<T> observableList = FXCollections.observableArrayList();
		
		for (T t : list) {
			observableList.add(t);
		}
		
		return observableList;
	}
}