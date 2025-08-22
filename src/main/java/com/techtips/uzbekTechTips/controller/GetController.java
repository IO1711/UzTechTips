package com.techtips.uzbekTechTips.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techtips.uzbekTechTips.services.GetDataService;
import com.techtips.uzbekTechTips.DTO.AddTopicDTO;
import com.techtips.uzbekTechTips.DTO.PreFetchTopicsDTO;
import com.techtips.uzbekTechTips.DTO.TopicContentDTO;
import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Topics;
import com.techtips.uzbekTechTips.model.Users;

import java.util.List;

@CrossOrigin(origins = {
    "https://techtipsuzbcreator.netlify.app",
    "https://techtipsuzb.netlify.app",
    "http://localhost:3000",
    "http://localhost:5173"
})
@RestController
@RequestMapping("/api/v1/")
public class GetController {

    @Autowired
    private GetDataService getDataService;

    @GetMapping("getApps")
    public List<Apps> getApps(){
        return getDataService.getAllApps();
    }


    /*
     * {"appName" : "samplename"}
     */
    @PostMapping("getAppTopics")
    public List<Topics> getAppTopics(@RequestBody Apps app){
        return getDataService.getTopicsForApp(app);
    }


    /*
     * {
     *  "app" : {
     *              "appName" : "sampleName"
     *          },
     *  "topic" : {
     *              "topicName" : "sampleName"
     *              }
     * }
     */
    @PostMapping("getTopicContent")
    public List<TopicContentDTO> getTopicContent(@RequestBody AddTopicDTO addTopicDTO){
        return getDataService.getTopicContent(addTopicDTO.getApp(), addTopicDTO.getTopic());
    }


    /*@PostMapping("getCreatorTopics")
    public List<Topics> getCReatorTopics(){
        
    }*/

    @GetMapping("getUserDetails")
    public Users getUserDetails(){
        return getDataService.getUserDetails();
    }


    @GetMapping("getAllTopics")
    public List<PreFetchTopicsDTO> getAllTopics(){
        return getDataService.getAllTopics();
    }

    
}
