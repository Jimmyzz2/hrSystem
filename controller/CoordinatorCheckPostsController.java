package controller;

import View.CoordinatorTableFiller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.others.*;

import java.util.ArrayList;

public class CoordinatorCheckPostsController implements Controller {

    private MainApp mainApp;
    private Coordinator coordinator;

    @FXML
    private TableView postTable;

    @FXML
    private TableView applicationTable;

    @FXML
    private Label info;

    @FXML
    private Label content;

    @FXML
    private Label applications;

    @FXML
    private Label note;

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
        CoordinatorTableFiller coordinatorTableFiller = new CoordinatorTableFiller(postTable);
        coordinatorTableFiller.fillPosts((ArrayList) coordinator.getPosts());
        info.setText("Postings by " + coordinator.getUsername() + ", at "
                + coordinator.getCompany().getCompanyName());
    }

    public void postDetail() {
        try {
            Post post = (Post) postTable.getSelectionModel().getSelectedItem();
            content.setText("Content:\n" + post.getContent());
            applications.setText("Applications under Post #" + post.getPostID());
            CoordinatorTableFiller coordinatorTableFiller = new CoordinatorTableFiller(applicationTable);
            coordinatorTableFiller.fillApplications((ArrayList) post.getApplications());
        } catch (NullPointerException w) {
            note.setText("Please select a Post.");
        }
    }

    public void applicationDetail() {
        try {
            Application application = (Application) applicationTable.getSelectionModel().getSelectedItem();

            Applicant applicant = application.getApplicant();
            String applied = "#" + application.getPostID();
            for (Post post : coordinator.getCompany().getPosts()) {
                for (Application app : applicant.getApplications()) {
                    if (!post.getPostID().equals(application.getPostID())
                            && post.getPostID().equals(app.getPostID())) {
                        applied += ", #" + post.getPostID();
                    }
                }
            }
            //logic?

            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.initOwner(mainApp.getPrimaryStage());
            VBox vBox = new VBox(20);
            vBox.getChildren().add(new Text("Applicant: " + applicant.getUsername() +
                    "\nCover Letter: " + application.getCoverLetter() + "\nCV: " + application.getCv() +
                    "\nApplied to Post: " + applied));
            Scene scene = new Scene(vBox, 300, 200);
            stage.setScene(scene);
            stage.show();

        } catch (NullPointerException w) {
            note.setText("Please select an Application.");
        }
    }

    public void back() {
        mainApp.setScene("/View/resource/Coordinator.fxml", coordinator);
        //path not sure
    }
}
