package poo.database;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
import poo.model.Patient;

import java.io.*;
import java.util.ArrayList;

public class ManageJson {


    private final Gson gson = new Gson();

    public void saveAppointment(Patient person) throws IOException {

      //  File fl = new File("datos/"+person.getId());

      //  if(isFileExists(fl))
      //  {
            String dir= "datos/"+person.getDate().getDate();
            File directory = new File(dir);
            directory.mkdirs();
            String json = new Gson().toJson(person);
            File file = new File(directory, String.valueOf(person.getId()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(json);
            bw.close();
       /* } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Cita ya existe con la cedula indicada");
            alert.showAndWait();
        }*/

    }

    public ArrayList<Patient> getAppointment(File folder) throws IOException {

        if (!folder.isDirectory()) {
            return null;
        } else {
            File[] files = folder.listFiles();
            ArrayList<Patient> dates = new ArrayList<>();
            assert files != null;
            for (File file : files) {
                if (!file.isDirectory()) {
                    FileReader fr = new FileReader(file);
                    BufferedReader b = new BufferedReader(fr);
                    String cadena = b.readLine();
                    Patient person = gson.fromJson(cadena, Patient.class);
                    dates.add(person);
                } else {
                    getAppointment(file);
                }
            }

            return dates;

        }
    }

    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
}
