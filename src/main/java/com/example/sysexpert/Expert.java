package com.example.sysexpert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class Expert {
    Stage stage;
    Scene scene;
    @FXML
    Label rulesLabel;

    @FXML
    TextField NomRegle;
    String filePath = "C:/Users/reyam/SysExpert/src/main/java/com/example/sysexpert/Rules.txt";

    @FXML
    private void OnRetour(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TypeConn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Type De Connection");
        stage.show();
    }

    String result = "";
    StringBuilder conclusionsConcatenated = new StringBuilder();
    @FXML
    public void OnAfficher(ActionEvent event) throws IOException {
        StringBuilder conclusionsConcatenated = new StringBuilder();
        int lineNumber = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 1) {
                    conclusionsConcatenated.append(line).append("\n"); // Append odd lines with newlines
                }
                lineNumber++;
            }
        }

        result = conclusionsConcatenated.toString();
        rulesLabel.setText(result);
    }

    @FXML
    private void OnModifier(ActionEvent event)throws IOException{
        String str = NomRegle.getText().toUpperCase();
        if (getLineNumber(filePath , str) != 0){
            
        }


    }

    // Fonction pour supprimer une ligne d'un fichier texte
    public static void deleteLineFromFile(String filePath, int lineNumber) throws IOException {
        String tempFile = "temp.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            int currentLineNumber = 0;
            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber != lineNumber) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        // Remplacer le fichier d'origine par le fichier temporaire
        new File(tempFile).renameTo(new File(filePath));
    }

    public static int getLineNumber(String filePath, String str) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            // Loop through each line in the file
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // Check if the line matches the entered string
                if (line.equals(str)) {
                    return lineNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // If the line is not found
        return 0;
    }


    public static String readLineFromFile(String path, int lineNumber) throws IOException {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int currentLineNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (++currentLineNumber == lineNumber) {
                    return line;
                }
            }
        }
        return line;
    }
}
