package model.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee extends User implements Visitor{

    private List<Application> incompleteApplications = new ArrayList<>();
    private List<Application> completeApplications = new ArrayList<>();


    public Referee(String username, String password) {
        super(username, password);
    }

    public List<Application> getIncompleteApplications() {
        return incompleteApplications;
    }

    public List<Application> getCompleteApplications() {
        return completeApplications;
    }



    @Override
    public void visit(Visitable visitable) {
        this.incompleteApplications.add((Application) visitable);
    }
}
