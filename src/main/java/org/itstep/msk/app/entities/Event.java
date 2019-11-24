package org.itstep.msk.app.entities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name")
    private String name;

    @Column(name = "eventdate")
    private Calendar date;

    public Event (){}

    public Event(Integer id, String name, Calendar date) {
        Id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
