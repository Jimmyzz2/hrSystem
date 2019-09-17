package View;

import controller.CoordinatorViewPostController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.InterviewManagers.InterviewManager;
import model.others.Interview;
import model.others.Post;

import java.util.Map;

public class CoordinatorViewPost {

    ListView displayOfPosts;
    Tab currentTab;
    CoordinatorViewPostController controller;

    public void setController(CoordinatorViewPostController coordinatorViewPostController) {
        controller = coordinatorViewPostController;
    }

    public void displayPosts(ListView displayOfPosts){
        this.displayOfPosts = displayOfPosts;
        ObservableList<Post> posts = FXCollections.observableArrayList();
        posts.addAll(controller.getCoordinator().getPosts());
        displayOfPosts.setItems(posts);
    }

    public void displayInterviews(TabPane displayOfInterviews){
        if(displayOfPosts.getSelectionModel().getSelectedItem() != null){
            Post post = (Post) displayOfPosts.getSelectionModel().getSelectedItem();
            for(InterviewManager interviewManager: post.getInterviewManagers()){
                Tab tab = new Tab(interviewManager.toString());
                AnchorPane tabContent = new AnchorPane();
                TableView tableView = generateTableView(interviewManager);
                tabContent.getChildren().add(tableView);
                tab.setContent(tabContent);
                displayOfInterviews.getTabs().add(tab);
            }
        };
    }

    public TableView generateTableView(InterviewManager interviewManager, Tab tab){
        CoordinatorInterviewsTableViewFactory factory = new CoordinatorInterviewsTableViewFactory();
        AnchorPane pane = new AnchorPane();
        tab.setContent(pane);
        TableView tableView = new TableView();
        TableColumn<Interview, String>  applicantColumn = new TableColumn<Interview, String>();
        TableColumn<Interview, String>  interviewerColumn = new TableColumn<Interview, String>();
        TableColumn<Interview, Date>  dateColumn = new TableColumn<Interview, String>();
        TableColumn<Interview, String>  applicantColumn = new TableColumn<Interview, String>();
        TableColumn<Interview, String>  applicantColumn = new TableColumn<Interview, String>();
    }
}
