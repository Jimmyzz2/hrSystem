package model.InterviewManagers;

import model.others.Application;
import model.others.Interview;
import model.InterviewManagers.InterviewManager;


import java.util.ArrayList;
import java.util.List;

import model.InterviewManagers.InterviewManager;

public class GroupInterviewManager implements InterviewManager {

    private List<Application> applications;
    private List<Interview> interviews;


    @Override
    public List<Application> getApplicationList() {
        return applications;
    }

    @Override
    public List<Application> getRecommendedList() {
        List recommendedList = new ArrayList<Application>();
        for (Interview interview: interviews){
            recommendedList.addAll(interview.getRecommendedApplications());
        }
        return recommendedList;
    }
}
