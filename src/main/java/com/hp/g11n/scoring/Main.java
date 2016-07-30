package com.hp.g11n.scoring;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Source Scoring!");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 200);
        primaryStage.setScene(scene);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        scene.setRoot(grid);

        //FileChooser
        DirectoryChooser chooser=new DirectoryChooser();

        //source file
        Label label1 = new Label("File to be processed:*:");
        Button browser1 = new Button("Browse...");

        final TextField sourceFile = new TextField();
        sourceFile.setText("c:/tmp/psl-generate-sorucescoring-report");
        browser1.setOnAction((final ActionEvent e) -> {
            File file = chooser.showDialog(primaryStage);
            if (file != null) {
                sourceFile.setText(file.getAbsolutePath());
            }
        });
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(sourceFile, 1, 0);
        GridPane.setConstraints(browser1, 2, 0);
        grid.getChildren().addAll(label1,sourceFile,browser1);

        //exclusion file path
        Label label2 = new Label("Exclusion File Path:");
        Button browser2 = new Button("Browse...");
        final TextField exclusionFile = new TextField();
        exclusionFile.setPromptText("");
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(exclusionFile, 1, 1);
        GridPane.setConstraints(browser2, 2, 1);
        grid.getChildren().addAll(label2,exclusionFile,browser2);

        //output folder
        Label label3 = new Label("Write Output to the folder:");
        Button browser3 = new Button("Browse...");
        final TextField outputFolder = new TextField();
        outputFolder.setText("c:/tmp/psl-generate-sorucescoring-report");
        browser3.setOnAction((final ActionEvent e) -> {
            File file = chooser.showDialog(primaryStage);
            if (file != null) {
                outputFolder.setText(file.getAbsolutePath());
            }
        });
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(outputFolder, 1, 2);
        GridPane.setConstraints(browser3, 2, 2);
        grid.getChildren().addAll(label3, outputFolder, browser3);

        //button
        Button submit = new Button("Run");
        GridPane.setConstraints(submit, 1, 3);
        grid.getChildren().add(submit);

        Button clear = new Button("Cancel");
        GridPane.setConstraints(clear, 2, 3);
        grid.getChildren().add(clear);

        //log textArea
        Label label4 = new Label("logs:");
        TextArea log = new TextArea();
        log.appendText("log will display here!");
        GridPane.setConstraints(label4,0,4);
        GridPane.setConstraints(log,1,4);
        grid.getChildren().addAll(label4,log);
        primaryStage.show();
    }
}
