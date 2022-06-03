package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField addAgeField;

    @FXML
    private TextField addNameField;

    @FXML
    private Button createValue;

    @FXML
    void initialize(){
        createValue.setOnAction(x ->{
            Controller controller = new Controller();
            User user = new User(addNameField.getText(), Integer.parseInt(addAgeField.getText()));
            UsersList.USERS.add(user);
            controller.getBase().insert(user);
            createValue.getScene().getWindow().hide();
        });
    }
}
