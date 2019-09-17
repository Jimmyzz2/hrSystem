package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InterviewManagers.GroupInterviewManager;
import model.InterviewManagers.InterviewManager;
import model.others.Interview;

public class CoordinatorInterviewsTableViewFactory {
    public void getTableView(InterviewManager interviewManager){
        if(interviewManager instanceof GroupInterviewManager){
            TableView tableView = new TableView();
            TableColumn<Interview, String> applicantColumn = new TableColumn<Interview, String>();
            applicantColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        }
    }
}
