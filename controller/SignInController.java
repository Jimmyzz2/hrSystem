package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.others.*;


import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class SignInController implements Controller, Initializable {

    private MainApp main;
    private User user;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signInLabel;

    public void changeToSignUp() {
        main.setScene("/view/resource/SignUp.fxml", null);
    }

    public void changeLabelText(){
        signInLabel.setText("Invalid username or password.");
    }

    public void checkSignInInfo() {
        this.user = getUser(usernameField.getText(), passwordField.getText());
        if(user == null){
            changeLabelText();
        }else if(user instanceof Applicant){
            //manage file list for user
//            main.getUserManager().getMyApplicant(usernameField.getText()).manageFile();
            main.setScene("/view/resource/Applicant.fxml", user);
        }else if(user instanceof Interviewer){
            main.setScene("/view/resource/Interviewer.fxml", user);
        }else if(user instanceof Coordinator){
            main.setScene("/view/resource/Coordinator.fxml", user);
        }else if(user instanceof Referee) {
            main.setScene("/view/resource/Referee.fxml", user);
        }else
            changeLabelText();


        Date today = Calendar.getInstance().getTime();

        //update status for all posts
//        for (Post post : main.getHrSystem().getPostManager()) {
//            if (today.after(post.getDateClosed())) {
//                post.toClosed();
//                //update status for its applications
//                for (Application application :
//                        main.getApplicationManager().getMyApplications(post.getApplications())) {
//                    application.toPending();
//                }
//            }
//        }
    }

    protected User getUser(String username, String password){
        UserManager userManager = main.getHrSystem().getUserManager();
        for(User user: userManager){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }return null;
    }

    protected User getUser(String username){
        UserManager userManager = main.getHrSystem().getUserManager();
        for(User user: userManager){
            if(username.equals(user.getUsername())){
                return user;
            }
        }return null;
    }



    public void setMainApp(MainApp main){
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
        signInLabel.setText("");
    }
}
