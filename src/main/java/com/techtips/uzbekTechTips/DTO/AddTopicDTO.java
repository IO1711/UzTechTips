package com.techtips.uzbekTechTips.DTO;


import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Topics;


public class AddTopicDTO {

    private Apps app;

    private Topics topic;


    public Apps getApp() {
        return this.app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }

    public Topics getTopic() {
        return this.topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

}
