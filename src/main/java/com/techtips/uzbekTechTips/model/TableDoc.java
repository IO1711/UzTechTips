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
public class TableDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String content;

    private int rowNumber;

    private int columnNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dataType_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Data dataType;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber(){
        return this.columnNumber;
    }

    public void setColumnNumber(int columnNumber){
        this.columnNumber = columnNumber;
    }

    public Data getDataType() {
        return this.dataType;
    }

    public void setDataType(Data dataType) {
        this.dataType = dataType;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            ", rowNumber='" + getRowNumber() + "'" +
            ", columnNumber='" + getColumnNumber() + "'" +
            ", dataType='" + getDataType() + "'" +
            "}";
    }

}
