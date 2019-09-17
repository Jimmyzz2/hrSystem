package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.others.Coordinator;
import model.others.Post;
import model.others.PostManager;
import model.others.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CoordinatorPostController implements Controller {
    private MainApp mainApp;
    private Coordinator coordinator;

    @FXML
    private Label note;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentField;

    @FXML
    private Label today;

    @FXML
    private TextField dateClosedField;

    @FXML
    private TextField refNum;

    @FXML
    private TextField refDeadline;


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
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strToday = dateFormat.format(date);
        today.setText("Date Posted: " + strToday + " (TODAY)");
    }


    public void post() {
        try {
            //arrange interviews also
            Date today = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date closedDate = formatter.parse(dateClosedField.getText());
            Date refDdl = formatter.parse(refDeadline.getText());
            if (today.after(closedDate)) {
                note.setText("Date Closed must be after Today");
            } else if (closedDate.after(refDdl)) {
                note.setText("Reference Deadline must be after Date Closed");
            } else {
                String title = titleField.getText();
                String content = contentField.getText();
                String numRef = refNum.getText();
                if (!title.isEmpty() && !content.isEmpty() && !numRef.isEmpty()) {
                    Post post = new Post(title, content, closedDate, Integer.valueOf(numRef), refDdl);
                    PostManager postManager = mainApp.getHrSystem().getPostManager();
                    postManager.addPost(post);

                    coordinator.addPost(post);
                    //add Observer pattern;

                    titleField.clear();
                    contentField.clear();
                    dateClosedField.clear();
                    refNum.clear();
                    refDeadline.clear();

                    note.setText("Successfully posted! The PostID is " + post.getPostID());
                } else {
                    note.setText("Please fill all fields");
                }
            }
        } catch (ParseException dateException) {
            note.setText("Wrong date format");
        }
    }
    //

    public void backCoordinator() {
        mainApp.setScene("/View/resource/Coordinator.fxml", coordinator);
        //path not sure
    }
}
