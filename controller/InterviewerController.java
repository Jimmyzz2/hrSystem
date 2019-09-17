package controller;

import View.VInterviewer;
import View.VMailbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.others.*;


import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterviewerController implements controller.Controller{
    private MainApp mainApp;
    private Interviewer interviewer;
    private VInterviewer vInterviewer;
    private Interview selectedInterview;
    private boolean showComplete;
    private TableView<Application> applicationInfo;

    @FXML
    private TableView<Interview> interviewDate;

    @FXML
    private TableColumn<Interview, Date> interviewDateCol;

    @FXML
    private Button recommendBtn;

    @FXML
    private Button unRecommendBtn;

    @FXML
    private Text applicationInfoLabel;

    @FXML
    private VBox vboxPane;

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setUser(User user) {
        this.interviewer = (Interviewer) user;
    }

    @Override
    public void loadData() {

    }

    @FXML
    public void initialize() {
        vInterviewer = new VInterviewer();
    }

    @FXML
    void showIncompleteInterviews(ActionEvent event) {
        this.showComplete = false;
        List<Interview> incompleteInterviews = this.interviewer.getIncompleteInterviews();
        vInterviewer.displayInterviews(interviewDate, interviewDateCol, incompleteInterviews, vboxPane);
        vInterviewer.setButtonsVisibility(recommendBtn, unRecommendBtn, !showComplete);
        vInterviewer.setApplicationLabel(applicationInfoLabel, showComplete);
    }

    @FXML
    void showCompletedInterviews(ActionEvent event) {
        this.showComplete = true;
        List<Interview> completedInterviews = this.interviewer.getCompleteInterviews();
        vInterviewer.displayInterviews(interviewDate, interviewDateCol, completedInterviews, vboxPane);
        vInterviewer.setButtonsVisibility(recommendBtn, unRecommendBtn, !showComplete);
        vInterviewer.setApplicationLabel(applicationInfoLabel, showComplete);
    }


    @FXML
    void getInterviewDetail(ActionEvent event) {
        Interview interview = interviewDate.getSelectionModel().getSelectedItem();
        if(interview != null) {
            this.selectedInterview = interview;
            if(showComplete) {
                vInterviewer.displayCompleteVBox(vboxPane,
                        interview.getRecommendedApplications(), interview.getUnRecommendedApplications());
            } else {
                applicationInfo = vInterviewer.displayIncompleteVBox(vboxPane, interview.getApplications());
            }
        }
    }

    @FXML
    void showMailbox(ActionEvent event) {
        VMailbox vMailbox = new VMailbox();
        MailBox mailBox = this.interviewer.getMailBox();
        List<Message> messages;
        if(mailBox != null) {
            messages = mailBox.getMessages();
        } else {
            messages = new ArrayList<>();
        }
        vMailbox.displayMailbox(messages);
    }


    @FXML
    void signOut(ActionEvent event) {
        mainApp.setScene("/view/resource/SignIn.fxml", null);
    }


    @FXML
    void unRecommendApplication(ActionEvent event) {
        Application application = applicationInfo.getSelectionModel().getSelectedItem();
        if(this.selectedInterview != null && application != null) {
            unrecommendApplication(application);
        }
    }

    @FXML
    void recommendApplication(ActionEvent event) {
        Application application = applicationInfo.getSelectionModel().getSelectedItem();
        if(this.selectedInterview != null && application != null) {
            recommendApplication(application);
        }
    }

    public void recommendApplication(Application application) {
        if(selectedInterview.getApplications().contains(application)) {
            selectedInterview.removeFromApplication(application);
            selectedInterview.addRecommendedApplication(application);
        }
        // unRecommenedApplicaitons are unrecommended forever.
        selectedInterview.addEditedTimesCount();
//        testPrint();
        reDisplay(selectedInterview.getEditedTimesCount());
    }

    public void unrecommendApplication(Application application) {
        if(this.selectedInterview.getApplications().contains(application)) {
            this.selectedInterview.removeFromApplication(application);
            this.selectedInterview.addUnRecommendedApplication(application);
        }
        if(this.selectedInterview.getRecommendedApplications().contains(application)) {
            this.selectedInterview.removeFromRecommendedApplication(application);
            this.selectedInterview.addUnRecommendedApplication(application);
        }
        selectedInterview.addEditedTimesCount();
//        testPrint();
        reDisplay(selectedInterview.getEditedTimesCount());
    }

    private void reDisplay(int editedTimes) {
        if(editedTimes == this.selectedInterview.getTotalEditedTimes()) {
            interviewer.addCompleteInterview(selectedInterview);
            interviewer.removeIncompleteInterview(selectedInterview);
            vInterviewer.clearInterviewDateTable(interviewDate);
            vInterviewer.clearApplicationInfoTable(applicationInfo);
        } else {
            vInterviewer.clearApplicationInfoTable(applicationInfo);
            vInterviewer.displayApplicationsDetail(applicationInfo, selectedInterview.getApplications());
        }
    }

    void testPrint() {
        System.out.println("---------- Application ----------------");
        for(Application application: selectedInterview.getApplications()) {
            System.out.println(application.getApplicant().getUsername());
        }
        System.out.println("---------- Recommended ----------------");
        for(Application application: selectedInterview.getRecommendedApplications()) {
            System.out.println(application.getApplicant().getUsername());
        }
        System.out.println("---------- Unrecommended ----------------");
        for(Application application: selectedInterview.getUnRecommendedApplications()) {
            System.out.println(application.getApplicant().getUsername());
        }
        System.out.println("edited Time " + selectedInterview.getEditedTimesCount() + " total"
                + this.selectedInterview.getTotalEditedTimes());
    }
}


