package model.others;
import model.InterviewManagers.InterviewManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class Post {
    private Integer PostID;
    private String title;
    private String content;
    private Date datePosted;
    private Date dateClosed;
    private List<Application> applications;
    private List<InterviewManager> interviewManagers;
    private PostStatus status;
    private Integer numReference;
    private Date deadlineDate;
    private Set<String> tag;


    public Post(String title, String content, Date dateClosed, Integer numReference, Date deadlineDate) {
        this.title = title;
        this.content = content;
        this.datePosted = Calendar.getInstance().getTime();
        this.dateClosed = dateClosed;
        this.applications = new ArrayList<>();
        this.interviewManagers = new ArrayList<>();
        //not sure

        this.status = PostStatus.OPEN;
        this.numReference = numReference;
        this.deadlineDate = deadlineDate;
        //deadline date should be after closed date.

        this.tag = new HashSet<>();
        //for post tag
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void addApplication(Application application){
        this.applications.add(application);
    }

    public Integer getNumReference() {
        return numReference;
    }

    public void removeApplication(Application application){
        this.applications.remove(application);
    }

    public void setPostID(Integer postID) {
        PostID = postID;
    }

    public Integer getPostID() {
        return PostID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public Set<String> getTag() {
        return tag;
    }

    public List<InterviewManager> getInterviewManagers() {
        return interviewManagers;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDatePostedString(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(datePosted);
    }

    public String getDateClosedString(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(dateClosed);
    }

    public String getDeadlineDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(deadlineDate);
    }


}