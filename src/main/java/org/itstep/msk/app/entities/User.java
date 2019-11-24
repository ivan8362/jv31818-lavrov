package org.itstep.msk.app.entities;

import javax.persistence.*;

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

    public User(){

    }

    public User(String firstName, String lastName, Integer vkid) {
//        this.id = id;
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
}
