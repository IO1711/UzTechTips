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
public class ImageWassabi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dataType_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Data dataType;

    public ImageWassabi(){
        
    }

    public ImageWassabi(String content, Data dataType){
        this.content = content;
        this.dataType = dataType;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Data getDataType() {
        return this.dataType;
    }

    public void setDataType(Data dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ImageWassabi imageWassabi = (ImageWassabi) o;
        return Objects.equals(content, imageWassabi.getContent());
    }

    @Override
    public int hashCode(){
        return Objects.hash(content, dataType);
    }
    
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            ", dataType='" + getDataType() + "'" +
            "}";
    }


}
