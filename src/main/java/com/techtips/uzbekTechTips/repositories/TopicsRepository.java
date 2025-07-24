package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.Apps;
import com.techtips.uzbekTechTips.model.Topics;
import com.techtips.uzbekTechTips.model.Users;

import java.util.List;

public interface TopicsRepository extends JpaRepository<Topics, Long>{
    List<Topics> findByAppNameId(long id);
    Topics findByTopicName(String topicNAme);
    Topics findByTopicNameAndAppName(String topicName, Apps appName);
    List<Topics> findByCreator(Users creator);
}
