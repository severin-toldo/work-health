package com.stoldo.work_health.gui.pages.main;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.stoldo.work_health.model.AbstractController;
import com.stoldo.work_health.model.Booking;
import com.stoldo.work_health.model.Chart;
import com.stoldo.work_health.model.ChartType;
import com.stoldo.work_health.model.UserLoginRequest;
import com.stoldo.work_health.service.BackendDelegator;
import com.stoldo.work_health.shared.util.JavaFxUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;


public class MainController extends AbstractController {
	
	/*
	 * Journal
	 * */
	@FXML
	private TextField bookingDateTextField;
	
	@FXML
	private TextField bookingTextField;
	
	@FXML
	private ComboBox<Chart> sollChartCb;
	
	@FXML
	private ComboBox<Chart> habenChartCb;
	
	@FXML
	private TextField bookingAmountTextField;
	
	@FXML
	private TableView<Booking> bookingTable;
	
	
	/*
	 * General Ledger
	 * */
	@FXML
	private TextField chartAccnoTextField;
	
	@FXML
	private TextField chartShortNameTextField;
	
	@FXML
	private TextField chartNameTextField;
	
	@FXML
	private ComboBox<ChartType> chartTypeCb;
	
	@FXML
	private TableView<Chart> chartsTable;
	
	private String jwtToken;
	

	@Override
	public void initialize() throws Exception {
		showLoginDialog();
		
		List<Chart> charts = BackendDelegator.getInstance().getCharts(jwtToken);
		chartTypeCb.setItems(JavaFxUtils.asObservableList(Arrays.asList(ChartType.values())));
		
		refreshBookings();
		refreshCharts(charts);
	}
	
	public void addBooking() throws Exception {
		Booking booking = new Booking();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = sdf.parse(bookingDateTextField.getText());
		booking.setDate(d);
		
		booking.setText(bookingTextField.getText());
		booking.setSollChart(sollChartCb.getValue());
		booking.setHabenChart(habenChartCb.getValue());
		booking.setAmount(new BigDecimal(bookingAmountTextField.getText()));
		
		BackendDelegator.getInstance().saveBooking(booking, jwtToken);
		refreshBookings();
		
		bookingDateTextField.setText(null);
		bookingTextField.setText(null);
		sollChartCb.setValue(null);
		habenChartCb.setValue(null);
		bookingAmountTextField.setText(null);
	}
	
	public void refreshBookings() throws Exception {
		List<Booking> bookings = BackendDelegator.getInstance().getBookings(jwtToken);
		
		int i = 0;
		
		for (TableColumn<Booking, ?> col : bookingTable.getColumns()) {
			
			switch (i) {
				case 0:
					col.setCellValueFactory(new PropertyValueFactory<>("tableDate"));
					break;
				case 1:
					col.setCellValueFactory(new PropertyValueFactory<>("text"));
					break;
				case 2:
					col.setCellValueFactory(new PropertyValueFactory<>("sollChart"));
					break;
				case 3:
					col.setCellValueFactory(new PropertyValueFactory<>("habenChart"));
					break;
				case 4:
					col.setCellValueFactory(new PropertyValueFactory<>("amount"));
					break;
			}
			
			i++;
		}
		
		bookingTable.setItems(JavaFxUtils.asObservableList(bookings));
		refreshCharts(BackendDelegator.getInstance().getCharts(jwtToken));
	}
	
	public void addChart() throws Exception {
		Chart chart = new Chart();
		chart.setAccno(chartAccnoTextField.getText());
		chart.setShortName(chartShortNameTextField.getText());
		chart.setName(chartNameTextField.getText());
		chart.setChartType(chartTypeCb.getValue());
		
		BackendDelegator.getInstance().saveChart(chart, jwtToken);
		refreshCharts(BackendDelegator.getInstance().getCharts(jwtToken));
		
		chartAccnoTextField.setText(null);
		chartShortNameTextField.setText(null);
		chartNameTextField.setText(null);
		chartTypeCb.setValue(null);
	}
	
	public void refreshCharts(List<Chart> charts) {
		int i = 0;
		
		for (TableColumn<Chart, ?> col : chartsTable.getColumns()) {
			
			switch (i) {
				case 0:
					col.setCellValueFactory(new PropertyValueFactory<>("accno"));
					break;
				case 1:
					col.setCellValueFactory(new PropertyValueFactory<>("name"));
					break;
				case 2:
					col.setCellValueFactory(new PropertyValueFactory<>("chartType"));
					break;
				case 3:
					col.setCellValueFactory(new PropertyValueFactory<>("balance"));
					break;
			}
			
			i++;
		}
		
		chartsTable.setItems(JavaFxUtils.asObservableList(charts));
		sollChartCb.setItems(JavaFxUtils.asObservableList(charts));
		habenChartCb.setItems(JavaFxUtils.asObservableList(charts));
	}
	
	public void showLoginDialog() {
		Dialog<Pair<String, String>> loginDialog = new Dialog<>();
		loginDialog.setTitle("Login");
		loginDialog.setHeaderText("Please enter your credentials.");

		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		loginDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane loginGridPane = new GridPane();
		loginGridPane.setHgap(10);
		loginGridPane.setVgap(10);
		loginGridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField emailTextField = new TextField();
		emailTextField.setPromptText("E-Mail");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Password");

		loginGridPane.add(new Label("E-Mail:"), 0, 0);
		loginGridPane.add(emailTextField, 1, 0);
		loginGridPane.add(new Label("Password:"), 0, 1);
		loginGridPane.add(passwordField, 1, 1);

		loginDialog.getDialogPane().setContent(loginGridPane);
		Platform.runLater(() -> emailTextField.requestFocus());

		loginDialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(emailTextField.getText(), passwordField.getText());
		    } else {
		    	System.exit(0);
		    }
		    
		    return null;
		});

		Optional<Pair<String, String>> result = loginDialog.showAndWait();

		result.ifPresent(emailPassword -> {
			UserLoginRequest ulr = new UserLoginRequest();
			ulr.setEmail(emailPassword.getKey());
			ulr.setPassword(emailPassword.getValue());
			
			String jwtToken = BackendDelegator.getInstance().login(ulr);
			this.jwtToken = jwtToken;
		});
	}
}
