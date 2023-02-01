package poo.controller;

import poo.database.ManageJson;
import poo.model.Appointment;
import poo.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable{
    public Button btnRegister;
    public ComboBox<String> cbTime;
    public DatePicker scheduleDate;
    public TextField textLastName;
    public TextField textName;
    public TextField textAge;
    public TextField textId;
    private Appointment appointment;
    private Patient person = new Patient();

    private ManageJson manageJson = new ManageJson();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    String formmatedDate;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> hours = FXCollections.observableArrayList();
        hours.add("8:00");
        hours.add("9:00");
        hours.add("10:00");
        hours.add("11:00");
        hours.add("12:00");
        hours.add("14:00");
        hours.add("15:00");
        hours.add("16:00");
        hours.add("17:00");
        hours.add("18:00");
        cbTime.setItems(hours);
    }
    public void onRegisterButtonClick(ActionEvent actionEvent) throws IOException, InterruptedException {

        if((!textId.getText().isEmpty()) &&
                (!textName.getText().isEmpty()) &&
                (!textLastName.getText().isEmpty()) &&
                (!textAge.getText().isEmpty()) &&
                (!scheduleDate.getEditor().getText().isEmpty())&&
                (!cbTime.getSelectionModel().isEmpty())) {

            LocalDate date = scheduleDate.getValue();
            formmatedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            appointment = new Appointment(formmatedDate, cbTime.getSelectionModel().getSelectedItem());

            if(validateNumericFields(textId.getText()) &&
            validateNumericFields(textAge.getText())) {
                person.setId(Long.parseLong(textId.getText()));
                person.setName(textName.getText());
                person.setLastName(textLastName.getText());
                person.setAge(Integer.parseInt(textAge.getText()));
                person.setDate(appointment);
                manageJson.saveAppointment(person);

                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Cita agendada correctamente");
                alert.showAndWait();

                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } else {
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("La cédula y la edad deben ser un número.");
                alert.showAndWait();
            }

        } else {
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("¡Debes llenar todos los campos!");
            alert.showAndWait();
        }

    }

    private boolean validateNumericFields (String field) {
      boolean isNumeric = (field != null && field.matches("[0-9]+"));
      return isNumeric;
    }
}
