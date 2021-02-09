package com.stoldo.work_health.shared.util;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.stoldo.work_health.model.AbstractController;
import com.stoldo.work_health.shared.constants.Constants;
import com.stoldo.work_health.shared.constants.TranslationKey;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFxUtils {
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_PATH);
	
	@SuppressWarnings("unchecked")
	public static <T extends Parent> T load(AbstractController controller) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(controller.getClass().getResource(controller.getFxmlFileName()));
		fxmlLoader.setController(controller);
		fxmlLoader.setResources(getResourceBundle());
		
		return (T) fxmlLoader.load();
	}
	
	public static Stage openSubWindow(AbstractController c, double width, double height, TranslationKey titleTranslationKey) {
		Stage subStage = new Stage();
		c.load(subStage);
		Scene subScene = new Scene(c.getParent(), width, height);
		subStage.setScene(subScene);
		subStage.setTitle(getResourceBundle().getString(titleTranslationKey.getKey()));
		subStage.initModality(Modality.APPLICATION_MODAL);
		subStage.show();
		
		return subStage;
	}
	
	public static String translate(TranslationKey translationKey) {
		return getResourceBundle().getString(translationKey.getKey());
	}
	
	public static String translate(TranslationKey translationKey, Object... params) {
		return MessageFormat.format(getResourceBundle().getString(translationKey.getKey()), params);
	}
	
	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public static <T> ObservableList<T> asObservableList(List<T> list) {
		ObservableList<T> observableList = FXCollections.observableArrayList();
		
		for (T t : list) {
			observableList.add(t);
		}
		
		return observableList;
	}
}