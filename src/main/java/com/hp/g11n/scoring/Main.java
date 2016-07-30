package com.hp.g11n.scoring;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Source Scoring!");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        scene.setRoot(grid);

        //source file
        Label label1 = new Label("File to be processed:*:");
        final TextField sourceFile = new TextField();
        sourceFile.setPromptText("");
        GridPane.setConstraints(label1,0 , 0);
        GridPane.setConstraints(sourceFile, 1, 0);
        grid.getChildren().addAll(label1,sourceFile);

        //exclusion file path
        Label label2 = new Label("Exclusion File Path:");
        final TextField exclusionFile = new TextField();
        exclusionFile.setPromptText("");
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(exclusionFile, 1, 1);
        grid.getChildren().addAll(label2,exclusionFile);

        //output folder
        Label label3 = new Label("Write Output to the folder:");
        final TextField outputFolder = new TextField();
        outputFolder.setPromptText("");
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(outputFolder, 1, 2);
        grid.getChildren().addAll(label3,outputFolder);

        //button
        Button submit = new Button("Run");
        GridPane.setConstraints(submit, 0, 3);
        grid.getChildren().add(submit);

        Button clear = new Button("Cancel");
        GridPane.setConstraints(clear, 1, 3);
        grid.getChildren().add(clear);

        primaryStage.show();
    }
}
