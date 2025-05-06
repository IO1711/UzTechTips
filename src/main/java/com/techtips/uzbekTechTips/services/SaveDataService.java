package com.techtips.uzbekTechTips.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtips.uzbekTechTips.DTO.AddDataDTO;
import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Data;
import com.techtips.uzbekTechTips.model.ImageWassabi;
import com.techtips.uzbekTechTips.model.ListDoc;
import com.techtips.uzbekTechTips.model.TableDoc;
import com.techtips.uzbekTechTips.model.Text;
import com.techtips.uzbekTechTips.model.Topics;
import com.techtips.uzbekTechTips.repositories.AppsRepository;
import com.techtips.uzbekTechTips.repositories.DataRepository;
import com.techtips.uzbekTechTips.repositories.ImageWassabiRepository;
import com.techtips.uzbekTechTips.repositories.ListDocRepository;
import com.techtips.uzbekTechTips.repositories.TableRepository;
import com.techtips.uzbekTechTips.repositories.TextRepository;
import com.techtips.uzbekTechTips.repositories.TopicsRepository;

import jakarta.transaction.Transactional;

@Service
public class SaveDataService {

    @Autowired
    private AppsRepository appsRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ListDocRepository listDocRepository;
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private ImageWassabiRepository imageWassabiRepository;


    @Transactional
    public String addApp(Apps app){
        appsRepository.save(app);

        return "success";
    }

    @Transactional
    public String addTopic(Apps app, Topics topic){
        Apps currentApp = appsRepository.findByAppName(app.getAppName());

        topic.setAppName(currentApp);

        topicsRepository.save(topic);

        return "success";
    }

    @Transactional
    public String addData(List<AddDataDTO> addDataDTOs){
        
        for(AddDataDTO addDataDTO : addDataDTOs){
            Apps app = appsRepository.findByAppName(addDataDTO.getAppName());
            Topics topic = topicsRepository.findByTopicNameAndAppName(addDataDTO.getTopicName(), app);
            if (topic == null) {
                throw new IllegalArgumentException("Topic not found: " + addDataDTO.getTopicName());
            }

            Data data = new Data();
            data.setDataType(addDataDTO.getDataType());
            data.setOrderNumber(addDataDTO.getOrderNumber());
            data.setTopicName(topic);

            dataRepository.save(data);


            if("TABLE".equals(addDataDTO.getDataType())){
                TableDoc table = (TableDoc) addDataDTO.getData();
                table.setDataType(data);
                tableRepository.save(table);
            } else if("TEXT".equals(addDataDTO.getDataType())){
                Text text = (Text) addDataDTO.getData();
                text.setDataType(data);
                textRepository.save(text);
            } else if("LIST".equals(addDataDTO.getDataType())){
                ListDoc list = (ListDoc) addDataDTO.getData();
                list.setDataType(data);
                listDocRepository.save(list);
            } else if("IMAGE".equals(addDataDTO.getDataType())){
                ImageWassabi img = (ImageWassabi) addDataDTO.getData();
                img.setDataType(data);
                imageWassabiRepository.save(img);
            }

            
        }

        return "success";
    }
}
