package model.others;



public class UserFactory {

    public User getUser(String userType, String username, String password){
        if (userType.equals("applicant")){
            return new Applicant(username, password);
        }
        else if (userType.equals("coordinator")){
            return new Coordinator(username, password);
        }
        else if (userType.equals("interviewer")) {
            return new Interviewer(username, password);
        }
        else if (userType.equals("referee")){
            return new Referee(username, password);
        }
        else {return null;}
    }

}

