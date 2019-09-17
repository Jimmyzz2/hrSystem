package controller;

import View.ApplicantFunctions;
import View.VMailbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.others.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicantController implements Controller, Initializable {

    private MainApp mainApp;
    private Applicant applicant;
    private ApplicantFunctions applicantFunctions;

    @FXML
    private AnchorPane displayPane;

    @FXML
    private Text displayTitleText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicantFunctions = new ApplicantFunctions();
        applicantFunctions.setApplicantController(this);
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setUser(User user) {
        this.applicant = (Applicant) user;
    }

    @Override
    public void loadData() {

    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public Applicant getApplicant() {
        return applicant;
    }


    @FXML
    void displayPosting(ActionEvent event) {
        applicantFunctions.displayPosting(displayPane, displayTitleText);
    }

    @FXML
    void signOut(ActionEvent event) {
        mainApp.setScene("/view/resource/SignIn.fxml", null);
    }

    @FXML
    void viewMyApplication(ActionEvent event) {
        applicantFunctions.viewMyApplication(displayPane, displayTitleText);

    }

    public void withdrawApplication(Application application){
        Integer postID = application.getPostID();
        Post post = findPost(postID);
        applicant.withdrawApplication(application);
        post.removeApplication(application);
        for (Referee referee: application.getReferenceMap().keySet()){
            referee.getIncompleteApplications().remove(application);
        }
        this.applicantFunctions.popUpWindowWithdrawApplication();
        applicantFunctions.viewMyApplication(displayPane, displayTitleText);

    }

    private Post findPost(Integer postID){
        for (Post post: mainApp.getHrSystem().getPostManager()){
            if (post.getPostID().equals(postID)){
                return post;
            }
        }
        return null;
    }

    @FXML
    void viewMyInbox(ActionEvent event) {
        VMailbox vMailbox = new VMailbox();
        MailBox mailBox = this.applicant.getMailBox();
        List<Message> messages;
        if(mailBox != null) {
            messages = mailBox.getMessages();
        } else {
            messages = new ArrayList<>();
        }
        vMailbox.displayMailbox(messages);
    }

    @FXML
    void viewProfile(ActionEvent event) {
        applicantFunctions.viewProfile(displayPane, displayTitleText);
    }


}
