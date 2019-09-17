package model.others;

import java.io.Serializable;

public class HRSystem implements Serializable {
    private UserManager userManager;
    private PostManager postManager;
    private CompanyManager companyManager;

    public HRSystem() {
        this.userManager = new UserManager();
        this.postManager = new PostManager();
        this.companyManager = new CompanyManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public PostManager getPostManager() {
        return postManager;
    }

    public CompanyManager getMainInterviewManager() {
        return companyManager;
    }

}
