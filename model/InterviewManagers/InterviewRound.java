package model.InterviewManagers;

import model.others.Application;
import model.others.Interview;
import model.others.Interviewer;

import java.util.Date;
import java.util.List;

public class InterviewRound {

    private List<Application> applications;
    private List<Interview> interviews;
    private String type;

    public List<Application> getApplications() {
        return applications;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public String getType() {
        return type;
    }

    public void setApplications(List<Application> applications){
        this.applications = applications;
    }

    public void createInterview(List<Application> applications, List<Interviewer> interviewers, Date interviewDate){
        Interview interview = new Interview(applications, interviewers, interviewDate);
        interviews.add(interview);
        this.applications.removeAll(applications);
    }
}
