package com.codecool.dungeoncrawl.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ImportBtn {
    public Button getImportButton() {
        return importButton;
    }

    private final Button importButton = new Button("Import");
    private Window window;
    private Long hp;
    private Long armor;
    private Long x;
    private Long y;
    private Long attack;
    private String name;
    public void addImportButton(GridPane ui){
        ui.add(importButton, 0, 600);
        importButton.setFocusTraversable(false);
    }

    private void setImportAction() {
        importButton.setOnAction(event -> {
            File selectedFilePath = selectFilePath();
            importDataFromJson(selectedFilePath.getPath());
        });
    }

    public File selectFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import file");
        return fileChooser.showOpenDialog(this.window);
    }
    public List<ArrayList> importDataFromJson(String filePath){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(filePath)) {
            ArrayList gameData = new ArrayList<>();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");

            Long x = (Long) jsonObject.get("x");

            Long y = (Long) jsonObject.get("y");

            Long hp = (Long) jsonObject.get("hp");

            Long attack = (Long) jsonObject.get("attack");

            Long armor = (Long) jsonObject.get("armor");


            gameData.add(0, name);
            gameData.add(1, x);
            gameData.add(2, y);

            return gameData;




        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
