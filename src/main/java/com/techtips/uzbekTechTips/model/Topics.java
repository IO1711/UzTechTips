package com.techtips.uzbekTechTips.model;


import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String topicName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private Users creator;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Apps appName;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Users getCreator(){
        return this.creator;
    }

    public void setCreator(Users creator){
        this.creator = creator;
    }

    public Apps getAppName() {
        return this.appName;
    }

    public void setAppName(Apps appName) {
        this.appName = appName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Topics topic = (Topics) o;
        return Objects.equals(appName, topic.getAppName()) &&
                Objects.equals(topicName, topic.getTopicName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(topicName, appName);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", creator='" + getCreator() + "'" +
            ", appName='" + getAppName() + "'" +
            "}";
    }

}
