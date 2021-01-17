package com.WEBAPP.WEBAPP.model;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;
    @Column(name = "activity")
    private String activity;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_act")
    private User user;

    @PreRemove
    public void checkUserAssociations(){
        setUser(null);
    }
    public Todo() {
    }


    public Long getIdT() {
        return idT;
    }

    public void setIdT(Long idT) {
        this.idT = idT;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}