package com.techtips.uzbekTechTips.DTO;

public class TopicContentDTO {

    private String dataType;

    private int orderNumber;

    private Object data;

    public TopicContentDTO(String dataType, int orderNumber, Object data){
        this.dataType = dataType;
        this.orderNumber = orderNumber;
        this.data = data;
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
