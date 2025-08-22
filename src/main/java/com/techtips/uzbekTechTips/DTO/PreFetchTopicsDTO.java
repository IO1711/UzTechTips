package com.techtips.uzbekTechTips.DTO;

public class PreFetchTopicsDTO {

    private long id;

    private String appName;

    private String topicName;

    public PreFetchTopicsDTO(long id, String appName, String topicName){
        this.id = id;
        this.appName = appName;
        this.topicName = topicName;
    }


    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
