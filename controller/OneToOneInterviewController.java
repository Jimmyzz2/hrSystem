package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.others.Coordinator;
import model.others.User;

import java.net.URL;
import java.util.ResourceBundle;

public class OneToOneInterviewController implements Controller, Initializable {

    private MainApp mainApp;
    private Coordinator coordinator;

    @FXML
    private ChoiceBox InterviewerChoiceBox;

    @FXML
    private Button createInterviewButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox ApplicantChoiceBox;

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void createInterview() {


    }

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


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
