package controller;

import View.CoordinatorViewPost;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import model.others.Coordinator;
import model.others.User;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorViewPostController implements Controller, Initializable {

    @FXML
    private ListView<?> displayOfPosts;

    @FXML
    private TabPane displayOfInterviews;

    @FXML
    private Button createInterviewButton;

    @FXML
    private Label currentRoundLabel;
    private CoordinatorViewPost coordinatorViewPost;

    private MainApp mainApp;
    private Coordinator coordinator;

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
        coordinatorViewPost.displayPosts(displayOfPosts);
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    @FXML
    void updateTabPane(MouseEvent event) {
        coordinatorViewPost.displayInterviews();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinatorViewPost = new CoordinatorViewPost();
        coordinatorViewPost.setController(this);
    }
}

