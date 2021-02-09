package com.stoldo.work_health.gui;


import org.apache.commons.lang3.StringUtils;

import com.stoldo.work_health.shared.util.JavaFxUtils;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractController {
	
	@Getter
	private Parent parent;
	
	@Getter
	@Setter
	private Stage stage;
	
	@FXML
	public abstract void initialize() throws Exception;
	
	public String getFxmlFileName() {
		return StringUtils.replace(getClass().getSimpleName(), "Controller", ".fxml");
	}
	
	public void load(Stage stage) {
		try {
			this.stage = stage;
			this.parent = JavaFxUtils.load(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
