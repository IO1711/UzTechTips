package com.techtips.uzbekTechTips.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Apps {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String appName;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Apps app = (Apps) o;

        return Objects.equals(appName, app.appName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(appName);
    }

    


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", appName='" + getAppName() + "'" +
            "}";
    }

}
