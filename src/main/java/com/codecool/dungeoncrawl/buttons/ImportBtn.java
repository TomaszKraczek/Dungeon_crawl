package com.codecool.dungeoncrawl.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class ImportBtn {
    Button importButton = new Button("Import");
    Window window;
    public void addImportButton(GridPane ui){
        ui.add(importButton, 0, 600);
        importButton.setFocusTraversable(false);
        importButton.setOnAction(event -> {
            File selectedFilePath = selectFilePath();
            System.out.println(selectedFilePath);
        });
    }
    private File selectFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import file");
        return fileChooser.showOpenDialog(this.window);
    }
}
