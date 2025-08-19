package com.techtips.uzbekTechTips.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techtips.uzbekTechTips.DTO.AddDataDTO;

import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Data;
import com.techtips.uzbekTechTips.model.ImageWassabi;
import com.techtips.uzbekTechTips.model.ListDoc;
import com.techtips.uzbekTechTips.model.TableDoc;
import com.techtips.uzbekTechTips.model.Text;
import com.techtips.uzbekTechTips.model.Topics;
import com.techtips.uzbekTechTips.model.Users;
import com.techtips.uzbekTechTips.repositories.AppsRepository;
import com.techtips.uzbekTechTips.repositories.DataRepository;
import com.techtips.uzbekTechTips.repositories.ImageWassabiRepository;
import com.techtips.uzbekTechTips.repositories.ListDocRepository;
import com.techtips.uzbekTechTips.repositories.TableRepository;
import com.techtips.uzbekTechTips.repositories.TextRepository;
import com.techtips.uzbekTechTips.repositories.TopicsRepository;
import com.techtips.uzbekTechTips.repositories.UsersRepository;

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
    @Autowired
    private UsersRepository usersRepository;
    
    private final ImageUploadService imageUploadService;

    @Autowired
    public SaveDataService(ImageUploadService imageUploadService){
        this.imageUploadService = imageUploadService;
    }


    @Transactional
    public String addApp(Apps app){
        List<Apps> existingApps = appsRepository.findAll();

        if(existingApps.contains(app)){
            return "fail";
        }

        appsRepository.save(app);

        return "success";
    }

    @Transactional
    public String addTopic(Apps app, Topics topic){
        Apps currentApp = appsRepository.findByAppName(app.getAppName());

        topic.setAppName(currentApp);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Users currentUser = usersRepository.findByUsername(auth.getName());

        topic.setCreator(currentUser);

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
                table.setId(null);
                table.setDataType(data);
                tableRepository.save(table);
            } else if("TEXT".equals(addDataDTO.getDataType())){
                Text text = (Text) addDataDTO.getData();
                text.setId(null);
                text.setDataType(data);
                textRepository.save(text);
            } else if("LIST".equals(addDataDTO.getDataType())){
                ListDoc list = (ListDoc) addDataDTO.getData();
                list.setId(null);
                list.setDataType(data);
                listDocRepository.save(list);
            } else if("IMAGE".equals(addDataDTO.getDataType())){
                ImageWassabi img = (ImageWassabi) addDataDTO.getData();
                img.setId(null);
                img.setDataType(data);
                imageWassabiRepository.save(img);
            }

            
        }

        return "success";
    }

    @Transactional
    public String editTopic(List<AddDataDTO> addDataDTOs){

        Apps currentApp = appsRepository.findByAppName(addDataDTOs.get(0).getAppName());
        Topics currentTopic = topicsRepository.findByTopicNameAndAppName(addDataDTOs.get(0).getTopicName(), currentApp);

        List<Data> allDataImages = dataRepository.findByTopicNameId(currentTopic.getId())
            .stream()
            .filter(data -> "IMAGE".equals(data.getDataType()))
            .collect(Collectors.toList());

        List<ImageWassabi> allImages = new ArrayList<>();

        for(Data image : allDataImages){
            allImages.add(imageWassabiRepository.findByDataTypeId(image.getId()));
        }

        List<ImageWassabi> newImages = new ArrayList<>();

        for(AddDataDTO addDataDTO : addDataDTOs){
            if("IMAGE".equals(addDataDTO.getDataType())){
                ImageWassabi tempImage = (ImageWassabi) addDataDTO.getData();
                newImages.add(tempImage);
            }
        }

        Set<String> newImageContents = newImages.stream()
            .map(ImageWassabi::getContent)
            .collect(Collectors.toSet());
        
        allImages.removeIf(img -> newImageContents.contains(img.getContent()));

        for(ImageWassabi imageToDelete : allImages){
            imageUploadService.deleteImage(imageToDelete.getContent());
        }



        if(deleteTopicContent(currentApp.getAppName(), currentTopic.getTopicName())=="success")
            addData(addDataDTOs);

        return "success" + allImages + " ; New Images: " + newImages;

    }

    @Transactional
    public String deletApp(Apps app){
        List<Topics> appTopics = topicsRepository.findByAppNameId(app.getId());

        for(Topics topic : appTopics){
            deleteTopic(app, topic);
        }

        appsRepository.delete(app);
        
        return "success";
    }





    @Transactional
    public String deleteTopic(Apps app, Topics topic){
        Apps parentApp = appsRepository.findByAppName(app.getAppName());
        deleteTopicContent(app.getAppName(), topic.getTopicName());

        Topics topicToDelete = topicsRepository.findByTopicNameAndAppName(topic.getTopicName(), parentApp);

        topicsRepository.delete(topicToDelete);
        
        return "Deleted topic: " + topic.getTopicName() + " of the app " + app.getAppName();
    }





    @Transactional
    public String deleteTopicContent(String appReceived, String topicReceived){
        Apps app = appsRepository.findByAppName(appReceived);
        Topics topic = topicsRepository.findByTopicNameAndAppName(topicReceived, app);

        List<Data> topicData = dataRepository.findByTopicNameId(topic.getId());

        for(Data data : topicData){
            if("TABLE".equals(data.getDataType())){
                TableDoc table = tableRepository.findByDataTypeId(data.getId());
                tableRepository.delete(table);
            } else if("TEXT".equals(data.getDataType())){
                Text text = textRepository.findByDataTypeId(data.getId());
                textRepository.delete(text);
            } else if("LIST".equals(data.getDataType())){
                ListDoc list = listDocRepository.findByDataTypeId(data.getId());
                listDocRepository.delete(list);
            } else if("IMAGE".equals(data.getDataType())){
                ImageWassabi img = imageWassabiRepository.findByDataTypeId(data.getId());
                imageWassabiRepository.delete(img);
            }

            dataRepository.delete(data);
        }
        return "success";
    }
}
