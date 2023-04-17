package com.stoldo.work_health.gui;


import org.apache.commons.lang3.StringUtils;
import org.codejargon.feather.Feather;

import com.stoldo.work_health.shared.util.JavaFxUtils;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.Getter;

public abstract class AbstractController {
	
	@Getter
	private Parent parent;
	
	protected Stage stage;
	protected Feather feather;
	
	
	public AbstractController(Feather feather) {
		this.feather = feather;
		feather.injectFields(this);
	}
	
	public String getFxmlFileName() {
		return StringUtils.replace(getClass().getSimpleName(), "Controller", ".fxml");
	}
	
	public void load(Stage stage) throws Exception {
		this.stage = stage;
		this.parent = JavaFxUtils.load(this);
	}
	
	@FXML
	public abstract void initialize() throws Exception;
	
}
