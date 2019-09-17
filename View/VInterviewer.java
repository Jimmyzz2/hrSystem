package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.others.Application;
import model.others.CV;
import model.others.CoverLetter;
import model.others.Interview;
import sun.java2d.pipe.AlphaPaintPipe;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VInterviewer {

    public void displayInterviews(TableView<Interview> interviewDate,
                                  TableColumn<Interview, Date> interviewDateCol,
                                  List<Interview> interviews, VBox vBox) {
        interviewDateCol.setCellValueFactory(new PropertyValueFactory<>("interviewDate"));
        ObservableList<Interview> incomplete = FXCollections.observableArrayList(interviews);
        interviewDate.setItems(incomplete);
    }

    public void displayApplicationsDetail(TableView<Application> applicationInfo, List<Application> applications) {
        applicationInfo.getColumns().clear();
        applicationInfo.getItems().clear();

        TableColumn<Application, String> applicantCol = new TableColumn<>("Applicant");
        applicantCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Application, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Application, String> param) {
                return new SimpleStringProperty(param.getValue().getApplicant().getUsername());
            }
        });

        TableColumn<Application, String> applyDateCol = new TableColumn<>("Apply Date");
        applyDateCol.setCellValueFactory(new PropertyValueFactory<>("dateAppliedString"));

        applicationInfo.getColumns().addAll(applicantCol, applyDateCol);
        ObservableList<Application> applicationsList = FXCollections.observableArrayList(applications);
        applicationInfo.setItems(applicationsList);

        applicationInfo.setRowFactory( tv -> {
            TableRow<Application> row = new TableRow<>();
            row.setOnMouseClicked(doubleClickEvent -> {
                if (doubleClickEvent.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Application rowData = row.getItem();
                    popUpWindowForApplicantProfile(rowData);
                }
            });
            return row ;
        });
    }

    public void displayCompleteVBox(VBox vboxPane, List<Application> recommended, List<Application> unRecommended) {
        Label recommendLabel = new Label();
        recommendLabel.setText("Recommended");
        TableView<Application> recommendedTable = new TableView<>();
        Label unRecommendLabel = new Label();
        unRecommendLabel.setText("Unrecommended");
        TableView<Application> unRecommendedTable = new TableView<>();

        vboxPane.getChildren().clear();
        vboxPane.setAlignment(Pos.CENTER);
        vboxPane.setSpacing(10);

        displayApplicationsDetail(recommendedTable, recommended);
        displayApplicationsDetail(unRecommendedTable, unRecommended);

        vboxPane.getChildren().addAll(recommendLabel, recommendedTable, unRecommendLabel, unRecommendedTable);
    }

    public TableView<Application>  displayIncompleteVBox(VBox vboxPane,
                                                         List<Application> applicationList) {
        TableView<Application> applicationInfo = new TableView<>();
        vboxPane.getChildren().clear();
        vboxPane.setAlignment(Pos.CENTER);

        displayApplicationsDetail(applicationInfo, applicationList);

        vboxPane.getChildren().addAll(applicationInfo);
        return applicationInfo;
    }


    private void
    popUpWindowForApplicantProfile(Application application){
        Stage window = new Stage();


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("More info");
        window.setMinWidth(250);

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> window.close());

        CV cv = application.getCv();
        CoverLetter coverLetter = application.getCoverLetter();
        String applicantInfo = "CV -- " + cv.getTitle() + "\n" + cv.getContent() + "\n\n";
        applicantInfo += "Cover letter -- " + coverLetter.getTitle() + "\n" + coverLetter.getContent()+ "\n";
        Text text = new Text();
        text.wrappingWidthProperty().set(300);
        text.setText(applicantInfo);
        text.setTextAlignment(TextAlignment.CENTER);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(text, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void clearInterviewDateTable(TableView<Interview> tableView) {
        tableView.getItems().clear();
    }

    public void clearApplicationInfoTable(TableView<Application> tableView) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
    }

    public void setButtonsVisibility(Button button1, Button button2, Boolean visible) {
        button1.setVisible(visible);
        button2.setVisible(visible);
    }

    public void setApplicationLabel(Text applicationInfoLabel, boolean showComplete) {
        if(showComplete) {
            applicationInfoLabel.setText("You have completed the interview. No change allowed.\n" +
                    "Please note that the recommendation results also depend on other interviewers, so " +
                    "the recommendation results in table might be changed by others."
            );
        } else {
            applicationInfoLabel.setText("Set interview complete/incomplete here");
        }
    }
}
