package model.others;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private List<Coordinator> coordinators;
    private List<Interviewer> interviewers;
    private List<Post> posts;

    public Company(String companyName) {
        this.companyName = companyName;
        coordinators = new ArrayList<>();
        interviewers = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
