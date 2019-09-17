package controller;
import model.others.User;

public interface Controller {
    void setMainApp(MainApp mainApp);
    void setUser(User user);

    void loadData();
}
