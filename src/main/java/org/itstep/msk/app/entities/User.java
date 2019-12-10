package org.itstep.msk.app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "vkid")
    private Integer vkid;

    @Column(name = "profilepicture")
    private String profilepicture;

    @ManyToMany(mappedBy = "users")
    private Set<Event> events = new HashSet<>();

    public User(){}

    public User(String firstName, String lastName, Integer vkid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vkid = vkid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getVkid() {
        return vkid;
    }

    public void setVkid(Integer vkid) {
        this.vkid = vkid;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
        event.getUsers().add(this);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
        event.getUsers().remove(this);
    }
}
