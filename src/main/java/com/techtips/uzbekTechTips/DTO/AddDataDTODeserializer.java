package com.techtips.uzbekTechTips.DTO;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtips.uzbekTechTips.model.ImageWassabi;
import com.techtips.uzbekTechTips.model.ListDoc;
import com.techtips.uzbekTechTips.model.TableDoc;
import com.techtips.uzbekTechTips.model.Text;

@Component
public class AddDataDTODeserializer extends JsonDeserializer<AddDataDTO>{

    @Override
    public AddDataDTO deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException{

        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        JsonNode node = mapper.readTree(p);

        AddDataDTO addDataDTO = new AddDataDTO();

        addDataDTO.setAppName(node.get("appName").asText());

        addDataDTO.setTopicName(node.get("topicName").asText());

        addDataDTO.setDataType(node.get("dataType").asText().toUpperCase());

        addDataDTO.setOrderNumber(node.get("orderNumber").asInt(0));

        if("TABLE".equals(addDataDTO.getDataType())){
            addDataDTO.setData(mapper.treeToValue(node.get("data"), TableDoc.class));
        } else if("LIST".equals(addDataDTO.getDataType())){
            addDataDTO.setData(mapper.treeToValue(node.get("data"), ListDoc.class));
        } else if("TEXT".equals(addDataDTO.getDataType())){
            addDataDTO.setData(mapper.treeToValue(node.get("data"), Text.class));
        } else if("IMAGE".equals(addDataDTO.getDataType())){
            addDataDTO.setData(mapper.treeToValue(node.get("data"), ImageWassabi.class));
        } else {
            throw new IllegalArgumentException("Unknown dataType: " + addDataDTO.getDataType());
        }

        return addDataDTO;

    }
}
