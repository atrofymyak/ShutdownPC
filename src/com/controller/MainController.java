package com.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MainController {

	@FXML
	private ListView<String> hoursView;

	@FXML
	private ListView<String> minutesView;
	
	@FXML
	private Button start;
	
	@FXML
	private Button cancel;

	private boolean run = false;
	
	@FXML
	protected void startAction(ActionEvent event) throws Exception {

		String hours = hoursView.getSelectionModel().getSelectedItem();
		String minutes = minutesView.getSelectionModel().getSelectedItem();

		int hour = 0;
		int minute = 0;

		if (hours != null && !hours.isEmpty()) {
			hour = Integer.valueOf(hours);
		}

		if (minutes != null && !minutes.isEmpty()) {
			minute = Integer.valueOf(minutes);
		}

		int interval = hour * 60 * 60 + minute * 60;
		
		if(interval == 0){
			interval = 300;
		}

		run = !run;
		
		if(run){						
			cancel.setDisable(false);
			start.setDisable(true);
			
			executeCommand("shutdown.exe -s -t " + interval);
		}
		
	}

	@FXML
	protected void cancelAction(ActionEvent event) throws Exception {

		run = !run;
		
		if(!run){									
			cancel.setDisable(true);
			start.setDisable(false);
			
			executeCommand("shutdown.exe -a");
		}
		
	}
	
	@FXML
	protected void selectHour(MouseEvent event) throws Exception {

		if (hoursView.getSelectionModel().getSelectedItem() != null) {
			start.setDisable(false);
		} else {
			start.setDisable(true);
		}
		
	}
	
	@FXML
	protected void selectMinute(MouseEvent event) throws Exception {

		if (minutesView.getSelectionModel().getSelectedItem() != null) {
			start.setDisable(false);
		} else {
			start.setDisable(true);
		}
	}

	@FXML
	protected void closeAction(ActionEvent event) throws Exception {

		System.exit(0);
	}
	
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
}
