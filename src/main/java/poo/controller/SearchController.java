package poo.controller;

import poo.database.ManageJson;
import poo.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public TableView<Patient> tableDates;
    public Button btnSearch;
    public DatePicker scheduledDate;
    public TableColumn<Patient,String> colID;
    public TableColumn<Patient,String> colName;
    public TableColumn<Patient, String> colHour;
    private final ManageJson manageJson = new ManageJson();
    private ObservableList<Patient> persons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persons = FXCollections.observableArrayList();
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colHour.setCellValueFactory(new PropertyValueFactory<>("Date"));

    }
    public void onSearchButtonClick(ActionEvent actionEvent) throws IOException {
        if(!scheduledDate.getEditor().getText().isEmpty()) {

            LocalDate date = scheduledDate.getValue();
            String formmatedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            File file = new File("datos/"+formmatedDate);
            if (manageJson.getAppointment(file) == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("No hay citas en esta fecha");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }else {
                ArrayList<Patient> dates = manageJson.getAppointment(file);
                persons.setAll(dates);
                tableDates.setItems(persons);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("¡Debes llenar el campo!");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

}
