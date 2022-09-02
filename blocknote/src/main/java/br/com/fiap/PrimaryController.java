package br.com.fiap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class PrimaryController {

    @FXML MenuItem miAbrir;
    @FXML MenuItem miSalvar;
    @FXML TextArea txtaNotepad;
    @FXML Label lbCountChar;

    public void open(){
        var fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        try {
            txtaNotepad.setText(Files.readString(file.toPath()));
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Arquivo não encontrado");
            alert.show();
        } catch (NullPointerException e){

        }
    }

    public void save(){
        var fileChooser = new FileChooser();
        var filter = new FileChooser.ExtensionFilter("Arquivo TXT (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        var file = fileChooser.showSaveDialog(null);
        
        try{
            PrintWriter output = new PrintWriter(file);
            output.print(txtaNotepad.getText());
            output.close();
        } catch(FileNotFoundException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Arquivo não encontrado");
            alert.show();
        } catch (NullPointerException e){
            
        }
    }

    public void close(){
        System.exit(0);
    }

    public void updateCount(){
        int count = 0;

        for(int i = 0; i < txtaNotepad.getText().length(); i++){
            count++;
        }

        lbCountChar.setText(count + " caracteres");

    }
}
