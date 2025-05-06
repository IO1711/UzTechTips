package com.techtips.uzbekTechTips.DTO;

public class TopicContentDTO {

    private String dataType;

    private int orderNumber;

    private Object content;

    public TopicContentDTO(String dataType, int orderNumber, Object content){
        this.dataType = dataType;
        this.orderNumber = orderNumber;
        this.content = content;
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

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
