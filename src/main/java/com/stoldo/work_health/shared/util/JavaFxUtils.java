package com.stoldo.work_health.shared.util;

import java.util.List;
import com.stoldo.work_health.gui.AbstractController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class JavaFxUtils {
	
	@SuppressWarnings("unchecked")
	public static <T extends Parent> T load(AbstractController controller) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(controller.getClass().getResource(controller.getFxmlFileName()));
		fxmlLoader.setController(controller);
		
		return (T) fxmlLoader.load();
	}
	
	public static <T> ObservableList<T> asObservableList(List<T> list) {
		ObservableList<T> observableList = FXCollections.observableArrayList();
		
		for (T t : list) {
			observableList.add(t);
		}
		
		return observableList;
	}
}