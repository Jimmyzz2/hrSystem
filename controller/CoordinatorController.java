package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.others.Coordinator;
import model.others.User;



public class CoordinatorController implements Controller{

    private MainApp mainApp;
    private Coordinator coordinator;

    @FXML
    private Label username;

    @FXML
    private Label company;

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setUser(User user) {
        this.coordinator = (Coordinator) user;
    }

    @Override
    public void loadData() {
        username.setText("Username: " + coordinator.getUsername());
        company.setText("Company: " + coordinator.getCompany().getCompanyName());
    }

    public void handleCheckPostButton() {
        mainApp.setScene("/View/resource/CoordinatorCheckPosts.fxml", coordinator);
        //path not sure
    }

    public void handlePostButton() {
        mainApp.setScene("/View/resource/CoordinatorPost.fxml", coordinator);
        //path not sure
    }

    public void handleSignOut() {
        mainApp.setScene("/View/resource/SignIn.fxml", null);
    }
}
