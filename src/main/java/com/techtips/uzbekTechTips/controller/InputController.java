package com.techtips.uzbekTechTips.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techtips.uzbekTechTips.services.SaveDataService;
import com.techtips.uzbekTechTips.DTO.AddDataDTO;
import com.techtips.uzbekTechTips.DTO.AddTopicDTO;
import com.techtips.uzbekTechTips.model.Apps;

import java.util.List;

@CrossOrigin(origins = {
    "https://techtipsuzbcreator.netlify.app",
    "https://techtipsuzb.netlify.app"
})
@RestController
@RequestMapping("/api")
public class InputController {

    @Autowired
    private SaveDataService saveDataService;

    /*
     * {"appName" : "samplename"}
     */
    @PostMapping("/v1/addApp")
    public String saveApp(@RequestBody Apps apps){
        
        return saveDataService.addApp(apps);

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
    @PostMapping("/v1/addTopic")
    public String saveTopic(@RequestBody AddTopicDTO addTopicDTO){
        return saveDataService.addTopic(addTopicDTO.getApp(), addTopicDTO.getTopic());
    }

    /*
     * [{
     *  "appName" : "sampleApp",
     *  "topicName" : "sampleName",
     *  "dataType" : "sampleData",
     *  "orderNumber" : 1,
     *  "data" : {"content" : "sampleContent", "rowNumber" : 1, "columnNumber" : 1}
     * }]
     */
    @PostMapping("/v1/addData")
    public String saveData(@RequestBody List<AddDataDTO> addDataDTO){
        return saveDataService.addData(addDataDTO);
    }
}
