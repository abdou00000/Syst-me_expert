package com.example.sysexpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilisateur {
    private Stage stage;
    private Scene scene;

    String allConclusions = "";

    @FXML
    TextArea faits;

    @FXML
    Label conclusion;

    @FXML
    Label message;

    private String parse(){
        String faits_ = faits.getText().toUpperCase();
        String[] faits_token = faits_.split(";");
        for (int i = 0; i < faits_token.length; i++) {
            faits_token[i] = faits_token[i].trim(); // Remove leading and trailing whitespace
        }
        String[] conclusions = new String[faits_token.length];
        for (int i = 0; i < faits_token.length; i++) {
            conclusions[i] = Donner_Conclusion(faits_token[i]);
        }

        StringBuilder conclusionsConcatenated = new StringBuilder();
        for (String conclusion : conclusions) {
            conclusionsConcatenated.append(conclusion).append("\n"); // Ajouter chaque conclusion suivie d'un saut de ligne
        }
        return conclusionsConcatenated.toString();
    }

    @FXML
    private void OnEnvoyer(ActionEvent event) throws IOException {
        allConclusions = parse();
        message.setText(allConclusions);
    }

    @FXML
    private void OnRetour2(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Utilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Session Utilisateur");
        stage.show();
    }

    @FXML
    private void OnRetour1(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TypeConn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Type De Connection");
        stage.show();
    }


    @FXML
    public String Donner_Conclusion(String str) {
        String filePath = "C:/Users/reyam/SysExpert/src/main/java/com/example/sysexpert/Rules.txt";
        String conclusion1 = ""; // Variable to store the conclusion

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean factFound = false; // Flag to track if fact is found

            // Loop through each line in the file
            while ((line = reader.readLine()) != null) {
                // Check if the line matches the entered fact
                if (line.equals(str)) {
                    factFound = true;
                    // Read the next line which contains the conclusion
                    conclusion1 = reader.readLine();
                    break;
                }
            }

            // If fact not found
            if (!factFound) {
                return "Solution non trouvée";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conclusion1;
    }

}
