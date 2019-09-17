package model.others;

import java.text.SimpleDateFormat;
import java.util.*;

public class Application extends Observable implements Visitable{
    private Applicant applicant;
    private CV cv;
    private CoverLetter coverLetter;
    private Date dateApplied;
    private String dateAppliedString;
    private Integer postID;
    private ApplicationStatus status;
    private Map<Referee, Reference> referenceMap;

    public Application(Applicant applicant, CV cv, CoverLetter coverLetter, Integer postID, Map<Referee, Reference> referenceMap) {
        this.applicant = applicant;
        this.cv = cv;
        this.coverLetter = coverLetter;
        this.dateApplied = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.dateAppliedString = formatter.format(this.dateApplied);
        this.postID = postID;
        this.status = ApplicationStatus.SUBMITTED;
        this.referenceMap = referenceMap;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public CoverLetter getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(CoverLetter coverLetter) {
        this.coverLetter = coverLetter;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getDateAppliedString() {
        return dateAppliedString;
    }

    public void setDateAppliedString(String dateAppliedString) {
        this.dateAppliedString = dateAppliedString;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Map<Referee, Reference> getReferenceMap() {
        return referenceMap;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void sendMessage(Message message){
        setChanged();
        notifyObservers(message);
    }

}
