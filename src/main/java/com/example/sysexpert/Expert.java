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
    Label MessageLabel;
    @FXML
    TextField EnonceRegle;

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
    private void OnSupprimer(ActionEvent event) throws IOException {
        if (!NomRegle.getText().isBlank()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
                StringBuilder content = new StringBuilder();
                String line;
                String str = NomRegle.getText().toUpperCase();
                boolean regleFound = false;


                while ((line = reader.readLine()) != null) {
                    if (!line.equalsIgnoreCase(str)) {
                        content.append(line).append("\n");
                    } else {
                        regleFound = true;
                        reader.readLine();
                    }
                }

                if (regleFound) {
                    // Write the modified content back to the file
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(content.toString());
                        // Display a success message
                        MessageLabel.setText("La régle a été Supprimée!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Display an error message if writing fails
                        MessageLabel.setText("Failed to modify line!");
                    }
                } else {
                    // Display a message if the old line was not found
                    MessageLabel.setText("Règle non trouvée , essayez encore (cliquez sur 'afficher les règles' pour consulter les règles existantes)");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else MessageLabel.setText("Pour pouvoir supprimer une règle, entrez son nom (cliquez sur 'afficher les règles' pour consulter les règles existantes)");
    }


    @FXML
    private void OnModifier(ActionEvent event) {
        if (NomRegle.getText().isBlank() == false && EnonceRegle.getText().isBlank() == false) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                StringBuilder content = new StringBuilder();
                String line;
                boolean regleFound = false;
                String str = NomRegle.getText().toUpperCase();
                String newLine = EnonceRegle.getText();

                while ((line = reader.readLine()) != null) {
                    if (line.equals(str)) {
                        regleFound = true;
                        content.append(line).append("\n");
                        reader.readLine();
                        content.append(newLine).append("\n");
                    } else {
                        // Keep the line unchanged
                        content.append(line).append("\n");
                    }
                }
                if (regleFound) {
                    // Write the modified content back to the file
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(content.toString());
                        // Display a success message
                        MessageLabel.setText("La régle a été modifiée!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Display an error message if writing fails
                        MessageLabel.setText("Failed to modify line!");
                    }
                } else {
                    // Display a message if the old line was not found
                    MessageLabel.setText("Règle non trouvée , essayez encore (cliquez sur 'afficher les règles' pour consulter les règles existantes)");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else MessageLabel.setText("Pour pouvoir modifier une règle, entrez son nom puis son nouveau enoncé");
    }


    @FXML
    private void OnAjouter(ActionEvent event){
        if (NomRegle.getText().isBlank() == false && EnonceRegle.getText().isBlank() == false) {
            try (FileWriter writer = new FileWriter(filePath , true)) {
                String str = NomRegle.getText().toUpperCase();
                String newLine = EnonceRegle.getText();
                writer.write(str + "\n" + newLine + "\n");

                MessageLabel.setText("La régle a été ajoutée!");
            } catch (IOException e) {
                e.printStackTrace();
                // Display an error message if writing fails
                MessageLabel.setText("Failed to add the rule!");
            }
        }
        else MessageLabel.setText("Pour pouvoir ajouter une règle, entrez son nom puis son enoncé");
    }

}
