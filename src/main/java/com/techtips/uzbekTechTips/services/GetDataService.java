package com.techtips.uzbekTechTips.services;


import org.springframework.stereotype.Service;

import com.techtips.uzbekTechTips.DTO.TopicContentDTO;
import com.techtips.uzbekTechTips.model.*;
import com.techtips.uzbekTechTips.repositories.AppsRepository;
import com.techtips.uzbekTechTips.repositories.DataRepository;
import com.techtips.uzbekTechTips.repositories.ImageWassabiRepository;
import com.techtips.uzbekTechTips.repositories.ListDocRepository;
import com.techtips.uzbekTechTips.repositories.TableRepository;
import com.techtips.uzbekTechTips.repositories.TextRepository;
import com.techtips.uzbekTechTips.repositories.TopicsRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class GetDataService {

    
    private AppsRepository appsRepository;
    
    private TopicsRepository topicsRepository;
    
    private DataRepository dataRepository;

    private TableRepository tableRepository;

    private ListDocRepository listDocRepository;

    private TextRepository textRepository;

    private ImageWassabiRepository imageWassabiRepository;

    private final Map<String, Function<Long, Object>> fetchMap = new HashMap<>();


    public GetDataService(
        AppsRepository appsRepository,
        TopicsRepository topicsRepository,
        DataRepository dataRepository,
        TableRepository tableRepository,
        ListDocRepository listDocRepository,
        TextRepository textRepository,
        ImageWassabiRepository imageWassabiRepository
    ){
        this.appsRepository = appsRepository;
        this.topicsRepository = topicsRepository;
        this.dataRepository = dataRepository;
        this.tableRepository = tableRepository;
        this.listDocRepository = listDocRepository;
        this.textRepository = textRepository;
        this.imageWassabiRepository = imageWassabiRepository;

        fetchMap.put("TABLE", id -> tableRepository.findByDataTypeId(id));
        fetchMap.put("LIST", id -> listDocRepository.findByDataTypeId(id));
        fetchMap.put("TEXT", id -> textRepository.findByDataTypeId(id));
        fetchMap.put("IMAGE", id -> imageWassabiRepository.findByDataTypeId(id));
    }






    public List<Apps> getAllApps(){
        return appsRepository.findAll();
    }





    public List<Topics> getTopicsForApp(Apps app){
        Apps currentApp = appsRepository.findByAppName(app.getAppName());

        List<Topics> topics = topicsRepository.findByAppNameId(currentApp.getId());
        return topics;
    }







    public List<TopicContentDTO> getTopicContent(Apps appName, Topics topicName){
        Apps app = appsRepository.findByAppName(appName.getAppName());

        Topics topic = topicsRepository.findByTopicNameAndAppName(topicName.getTopicName(), app);

        List<Data> topicDataList = dataRepository.findByTopicNameId(topic.getId());

        List<TopicContentDTO> topicContent = new ArrayList<>();

        topicDataList.sort(Comparator.comparingInt(Data::getOrderNumber));

        for(Data topicData : topicDataList){
            Function <Long, Object> fetchFn = fetchMap.get(topicData.getDataType());

            Object data = fetchFn.apply(topicData.getId());

            topicContent.add(new TopicContentDTO(topicData.getDataType(), topicData.getOrderNumber(), data));
        }

        

        return topicContent;
    }

}
