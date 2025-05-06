package com.techtips.uzbekTechTips.model;

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
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String dataType;

    private int orderNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Topics topicName;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public Topics getTopicName() {
        return this.topicName;
    }

    public void setTopicName(Topics topicName) {
        this.topicName = topicName;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", topicName='" + getTopicName() + "'" +
            "}";
    }

}
