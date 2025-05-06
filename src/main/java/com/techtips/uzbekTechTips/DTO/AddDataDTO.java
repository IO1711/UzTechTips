package com.techtips.uzbekTechTips.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;



@JsonDeserialize(using = AddDataDTODeserializer.class)
public class AddDataDTO {

    private String appName;

    private String topicName;

    private String dataType;

    private int orderNumber;

    private Object data;

    public String getAppName(){
        return this.appName;
    }

    public void setAppName(String appName){
        this.appName = appName;
    }

    public String getTopicName(){
        return this.topicName;
    }

    public void setTopicName(String topicName){
        this.topicName = topicName;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
