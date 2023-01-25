package com.codecool.dungeoncrawl.buttons;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportBtn {
    private Button exportButton = new Button("Export");
    private Window window;




    public void addExportButton(GridPane ui, Player player){
        ui.add(exportButton, 0, 500);
        exportButton.setFocusTraversable(false);
        exportButton.setOnAction(event -> {
            File selectedFilePath = selectFilePath();
            makeJson(selectedFilePath, player);
        });
    }

    private void makeJson(File selectedFile, Player player) {
        JSONObject jsonObject = new JSONObject();
        insertDataToJsonObject(jsonObject, player);

        tryToSaveFile(selectedFile, jsonObject);
        System.out.println("JSON file created: "+jsonObject);
    }

    private void insertDataToJsonObject(JSONObject jsonObject, Player player) {
        jsonObject.put("name", player.getName());
        jsonObject.put("x", player.getX());
        jsonObject.put("y", player.getY());
        jsonObject.put("hp", player.getHealth());
        jsonObject.put("attack", player.getAttackStrength());
        jsonObject.put("armor", player.getArmorPoints());
    }

    private void tryToSaveFile(File selectedFile, JSONObject jsonObject) {
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
