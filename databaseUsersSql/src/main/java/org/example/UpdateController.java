package org.example;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class UpdateController {
    private User user;

    @FXML
    private TextField ageTxt;

    @FXML
    private Button button;

    @FXML
    private TextField nameTxt;

    @FXML
    void initialize(){
    }

    @FXML
    void update(ActionEvent event) {
        Controller controller = new Controller();
        for (int i = 0; i < UsersList.USERS.size(); i++) {
            if (UsersList.USERS.get(i).equals(user)){
                UsersList.USERS.get(i).setName(nameTxt.getText());
                UsersList.USERS.get(i).setAge(Integer.parseInt(ageTxt.getText()));
                controller.getBase().update(UsersList.USERS.get(i));
                button.getScene().getWindow().hide();
            }
        }
    }
    public void setData(User user){
        ageTxt.setText(String.valueOf(user.getAge()));
        nameTxt.setText(user.getName());
        this.user = user;
    }
}
