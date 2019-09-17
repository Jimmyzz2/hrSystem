package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.others.*;


public class AddFileController {

    private User user;
    private String fileType;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea contentTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Text alertText;

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void submit() {
        String title = titleTextField.getText();
        String content = contentTextField.getText();
        saveFile(title,content);
    }

    @FXML
    void upload(){
        UploadFileController up = new UploadFileController();
        up.pickFile();
        saveFile(up.getTitle(),up.getContent());

    }

    void saveFile(String title,String content){
        if (title.equals("") || content.equals("")){
            alertText.setText("Invalid Information");
        }
        else{
            Applicant applicant = (Applicant) user;
            if (fileType.equals("CoverLetter")){
                CoverLetter coverLetter = new CoverLetter(title, content);
                applicant.addCoverLetter(coverLetter);
            }
            else if (fileType.equals("CV")){
                CV cv = new CV(title, content);
                applicant.addCV(cv);
            }
            cancel();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setType(String fileTyle){
        this.fileType = fileTyle;
    }

}