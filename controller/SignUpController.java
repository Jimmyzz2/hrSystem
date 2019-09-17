package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.others.User;
import model.others.UserFactory;
import model.others.UserManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Controller, Initializable{

    private MainApp main;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpLabel;

    @FXML
    private ChoiceBox<String> userTypeChoice;

    protected boolean verifyValidUsername(String username){
        UserManager userManager = main.getHrSystem().getUserManager();
        for(User user: userManager){
            if(username.equals(user.getUsername()))
                return false;
        }return true;
    }


    public void signUp() {
        String type = userTypeChoice.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserFactory userFactory = new UserFactory();
        if(verifyValidUsername(username)) {
            if (type == null) {
                signUpLabel.setText("choose a user type");
            } else {
                User user = userFactory.getUser(type, username, password);
                main.getHrSystem().getUserManager().addUser(user);
                changeToSignIn();
            }
        }
        else if(type.equals("referee")){
            SignInController signInController = new SignInController();
            signInController.setMainApp(main);
            User user = signInController.getUser(username);
            user.setPassword(password);
            changeToSignIn();}
        else{
            signUpLabel.setText("Invalid username.");
        }
    }


    public void changeToSignIn() {
        main.setScene("/view/resource/SignIn.fxml", null);
    }

    @Override
    public void setMainApp(MainApp main) {
        this.main = main;
    }

    @Override
    public void setUser(User user) {

    }

    @Override
    public void loadData() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpLabel.setText("");
        userTypeChoice.setItems(FXCollections.observableArrayList("applicant", "interviewer", "coordinator", "referee"));
    }
}
