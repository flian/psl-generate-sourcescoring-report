package com.hp.g11n.scoring.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by foy on 2016-08-05.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainViewController {

    @FXML
    private Parent root;

    @FXML
    private TextField sourceUrl;
    @FXML
    private TextField exclusionPattern;

    @FXML
    private TextField outputUrl;

    private DirectoryChooser chooser;
    @PostConstruct
    public void init() {
        if(chooser == null){
            chooser = new DirectoryChooser();
        }
    }
    @FXML
    public void chooseSource(ActionEvent event){
        File file = chooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            sourceUrl.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void chooseExclusion(ActionEvent event){
        File file = chooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            exclusionPattern.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void chooseOutput(ActionEvent event){
        File file = chooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            outputUrl.setText(file.getAbsolutePath());
        }
    }
}
