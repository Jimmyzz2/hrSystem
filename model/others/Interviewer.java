package model.others;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Interviewer extends User implements Visitor{

    private List<Interview> incompleteInterviews;
    private List<Interview> completeInterviews;

    public Interviewer(String username, String password){
        super(username, password);
        this.incompleteInterviews = new ArrayList<>();
        this.completeInterviews = new ArrayList<>();
    }

    public List<Interview> getIncompleteInterviews() {
        return incompleteInterviews;
    }

    public List<Interview> getCompleteInterviews() {
        return completeInterviews;
    }


    public void addCompleteInterview(Interview interview) {
        this.completeInterviews.add(interview);
    }

    public void removeIncompleteInterview(Interview interview) {
        this.incompleteInterviews.remove(interview);
    }

    @Override
    public void visit(Visitable visitable) {
        incompleteInterviews.add((Interview) visitable);
    }

    // testing
    public void setIncompleteInterviews(List<Interview> incompleteInterviews) {
        this.incompleteInterviews = incompleteInterviews;
    }
}
