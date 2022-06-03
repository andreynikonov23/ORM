package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private final Stage STAGE_ADD = new Stage();

    private final DataBase BASE = DataBase.getInstance();

    @FXML
    private TableColumn<User, IntegerProperty> ageColumn;

    @FXML
    private TableColumn<User, IntegerProperty> idColumn;

    @FXML
    private TableColumn<User, StringProperty> nameColumn;

    @FXML
    private TableView<User> tableView;


    @FXML
    void initialize(){
        BASE.read();
        tableView.setItems(UsersList.USERS);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

    }

    public void add(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addWindow.fxml"));
        try {
            STAGE_ADD.setScene(new Scene(loader.load()));
            STAGE_ADD.setTitle("add value");
            STAGE_ADD.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(ActionEvent event) throws IOException {
        User user = tableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateWindow.fxml"));
            Stage stage = new Stage();
            stage.show();
            stage.setTitle("update value");
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            UpdateController updateController = loader.getController();
            updateController.setData(tableView.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    void delete(ActionEvent event) {
        User user =tableView.getSelectionModel().getSelectedItem();
        if (user != null){
            for (int i = 0; i < UsersList.USERS.size(); i++) {
                if (UsersList.USERS.get(i).equals(user)){
                    getBase().delete(UsersList.USERS.get(i));
                    UsersList.USERS.remove(i);
                    i--;
                }
            }
        }
    }

    public DataBase getBase(){
        return BASE;
    }
    public TableView<User> getTableView(){
        return tableView;
    }
}
