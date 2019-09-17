package model.others;

import java.util.*;

public class Interview extends Observable implements Visitable{
    private List<Application> applications;
    private List<Interviewer> interviewers;
    private Date interviewDate;
    private List<Application> recommendedApplications;
    private List<Application> unRecommendedApplications;
    private Integer editedTimesCount;

    public Interview(List<Application> applications, List<Interviewer> interviewers, Date interviewDate) {
        this.applications = applications;
        this.interviewers = interviewers;
        this.interviewDate = interviewDate;
        this.recommendedApplications = new ArrayList<>();
        this.unRecommendedApplications = new ArrayList<>();
        this.editedTimesCount = 0;
    }

    public void notifyInterview(Message message){
        setChanged();
        notifyObservers(message);
    }

    public void removeFromApplication(Application application) {
        this.applications.remove(application);
    }

    public void removeFromRecommendedApplication(Application application) {
        this.applications.remove(application);
    }

    public void addRecommendedApplication(Application application) {
        this.recommendedApplications.add(application);
    }

    public void addUnRecommendedApplication(Application application) {
        this.unRecommendedApplications.add(application);
    }

    public List<Application> getUnRecommendedApplications() {
        return unRecommendedApplications;
    }


    public Integer getTotalEditedTimes(){
        return (applications.size() + recommendedApplications.size() + unRecommendedApplications.size())
                * interviewers.size();
    }

    public Integer getEditedTimesCount() {
        return editedTimesCount;
    }


    public List<Application> getRecommendedApplications() {
        return recommendedApplications;
    }


    public List<Application> getApplications() {
        return applications;
    }


    public List<Interviewer> getInterviewers() {
        return interviewers;
    }


    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setApplications(Message message){
        setChanged();
        notifyObservers(message);
    }

    public void addEditedTimesCount() {
        this.editedTimesCount++;
    }
}
