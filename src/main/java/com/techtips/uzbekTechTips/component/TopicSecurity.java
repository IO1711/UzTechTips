package com.techtips.uzbekTechTips.component;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.techtips.uzbekTechTips.DTO.AddDataDTO;
import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Topics;
import com.techtips.uzbekTechTips.repositories.AppsRepository;
import com.techtips.uzbekTechTips.repositories.TopicsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TopicSecurity {

    private TopicsRepository topicsRepository;

    


    @Transactional
    public boolean canEditAddData(List<AddDataDTO> addDataDTOs, Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated() || addDataDTOs==null || addDataDTOs.isEmpty()) return false;

        String username = authentication.getName();
        
        return topicsRepository.existsByTopicNameAndAppName_AppNameAndCreator_Username(addDataDTOs.get(0).getTopicName(),
                             addDataDTOs.get(0).getAppName(), username);


    }

}
