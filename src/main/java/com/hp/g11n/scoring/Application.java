package com.hp.g11n.scoring;

import com.hp.g11n.scoring.config.AbstractJavaFxApplicationSupport;
import com.hp.g11n.scoring.config.ConfigurationControllers;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

/**
 * Created by foy on 2016-08-05.
 */
@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {



    @Qualifier("mainView")
    @Autowired
    private ConfigurationControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Source Scoring !!");
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
