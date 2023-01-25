package com.codecool.dungeoncrawl.buttons;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportBtn {
    private Button exportButton = new Button("Export");
    private Window window;


    public void addExportButton(GridPane ui){
        ui.add(exportButton, 0, 500);
        exportButton.setFocusTraversable(false);
        exportButton.setOnAction(event -> {
            File selectedFilePath = selectFilePath();
            makeJson(selectedFilePath);
        });
    }

    private static void makeJson(File selectedFile) {
        JSONObject jsonObject = new JSONObject();
        insertDataToJsonObject(jsonObject);

        tryToSaveFile(selectedFile, jsonObject);
        System.out.println("JSON file created: "+jsonObject);
    }

    private static void insertDataToJsonObject(JSONObject jsonObject) {
        jsonObject.put("name", "Mariusz");
        jsonObject.put("x", "420");
        jsonObject.put("y", "420");
    }

    private static void tryToSaveFile(File selectedFile, JSONObject jsonObject) {
        try {
            FileWriter file = new FileWriter(selectedFile);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File selectFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export file");
        return fileChooser.showSaveDialog(this.window);
    }

    private String askUserForInput(String text) {
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText(text);
        td.showAndWait();
        return td.getEditor().getText();
    }
}
