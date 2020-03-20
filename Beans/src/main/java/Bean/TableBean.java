package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DTO.User;
import Service.UserService;


import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ViewScoped
public class TableBean implements Serializable, SelectableDataModel<User> {
    private List<User> users;
    private User selectedUser;
    private String inputtedName;
    private LocalDate inputtedDate;
    private String inputtedGender;
    private UserService service;

    public void setService(UserService service) {
        this.service = service;
    }

    public void init() {
        if (FacesContext.getCurrentInstance().isPostback()) {

        } else {
            service = new UserService();
            users = new ArrayList<User>();
            users = service.getAllUsers();
        }
    }

    public void update() {
        service = new UserService();
        User user = selectedUser;
        service.updateUser(user);

        selectedUser = null;

        users = service.getAllUsers();
    }

    public void add() {
        service = new UserService();
        User user = new User();
        user.setUserName(inputtedName);
        user.setBirthDate(inputtedDate);
        user.setUserGender(inputtedGender);
        service.insertUser(user);

        users = service.getAllUsers();

        inputtedName = "";
        inputtedDate = null;
        inputtedGender = "";
    }

    public void delete() {
        service = new UserService();
        service.deleteUser(selectedUser.getUserId());

        selectedUser = null;

        users = service.getAllUsers();
    }

    public String getInputtedName() {
        return inputtedName;
    }

    public void setInputtedName(String inputtedName) {
        this.inputtedName = inputtedName;
    }

    public LocalDate getInputtedDate() {
        return inputtedDate;
    }

    public void setInputtedDate(LocalDate inputtedDate) {
        this.inputtedDate = inputtedDate;
    }

    public String getInputtedGender() {
        return inputtedGender;
    }

    public void setInputtedGender(String inputtedGender) {
        this.inputtedGender = inputtedGender;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    @Override
    public Object getRowKey(User user) {
        return user.getUserId();
    }

    @Override
    public User getRowData(String rowKey) {
        int iRowKey = Integer.parseInt(rowKey);
        for (User user : users) {
            if (user.getUserId() == iRowKey) {
                return user;
            }
        }
        return null;
    }

    public void onRowSelect(SelectEvent<User> event) {
        FacesMessage msg = new FacesMessage("Пользователь с ID", event.getObject().getUserId().toString()
                + " выбран");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}

