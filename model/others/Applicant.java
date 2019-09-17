package model.others;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {

    private List<CV> listOfCV;
    private List<CoverLetter> listOfCoverLetter;
    private List<Application> applications;


    public Applicant(String username, String password){
        super(username, password);
        this.listOfCoverLetter = new ArrayList<>();
        this.listOfCV = new ArrayList<>();
        this.applications = new ArrayList<>();
    }

    public List<CV> getListOfCV() {
        return listOfCV;
    }

    public List<CoverLetter> getListOfCoverLetter() {
        return listOfCoverLetter;
    }

    public void addCoverLetter(CoverLetter coverLetter){
        this.listOfCoverLetter.add(coverLetter);
    }

    public void addCV(CV cv){
        this.listOfCV.add(cv);
    }

    public void deleteCV(String title) {
        for (CV cv : listOfCV) {
            if (cv.getTitle().equals(title)) {
                listOfCV.remove(cv);
                return;
            }
        }
    }

    public void deleteCoverLetter(String title) {
        for (CoverLetter coverLetter : listOfCoverLetter) {
            if (coverLetter.getTitle().equals(title)) {
                listOfCoverLetter.remove(coverLetter);
                return;
            }
        }
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application){
        applications.add(application);
    }

    public void withdrawApplication(Application application){applications.remove(application);}

}
