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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelloController {
    private Scene scene;
    private Stage stage;

    @FXML
    private Label WelcomeText;

    @FXML
    private TextField Pseudo;

    @FXML
    private TextField PassWord;


    @FXML
    private void onLogIn(ActionEvent event) {
        if (Pseudo.getText().isBlank() == false && PassWord.getText().isBlank() == false) {
            validateLogIn(event);
        } else WelcomeText.setText("SVP entrez votre Pseudo et votre mot de passe");
    }

    @FXML
    private void onExpert(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ConnExpert.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Connection Expert");
        stage.show();
    }

    @FXML
    private void onUtilisateur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Utilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Session Utilisateur");
        stage.show();
    }

    @FXML
    public void validateLogIn(ActionEvent event){
        String filePath = "C:/Users/reyam/SysExpert/src/main/java/com/example/sysexpert/Utilisateurs.txt";

        // Using try-with-resources to automatically close the resources
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String pseudo = Pseudo.getText();
            String password = PassWord.getText();
            String line;

            boolean pseudoFound = false; // Flag to track if pseudo is found

            // Loop through each line in the file
            while ((line = reader.readLine()) != null) {
                // Check if the line matches the entered pseudo
                if (line.equals(pseudo)) {
                    pseudoFound = true;

                    // Read the next line for password
                    line = reader.readLine();

                    // Check if password matches
                    if (line != null && line.equals(password)) {
                        // Log in successfully
                        LogInExpert(event);
                        return; // No need to continue searching
                    } else {
                        // Wrong password
                        WelcomeText.setText("Mot de passe incorrect, veuillez réessayer!");
                        return; // No need to continue searching
                    }
                }
            }

            // If pseudo not found
            if (!pseudoFound) {
                WelcomeText.setText("Utilisateur introuvable, veuillez réessayer!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void LogInExpert(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Expert.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Session Expert");
        stage.show();
    }

    @FXML
    private void OnRetour(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TypeConn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Type De Connection");
        stage.show();
    }

}