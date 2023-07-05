package numble.mbti.domain.features.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import numble.mbti.domain.features.contstant.FeaturesAttribute;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import numble.mbti.global.exception.BusinessException;
import numble.mbti.global.exception.constant.ErrorCode;

@Converter
public class FeaturesContentConvert implements AttributeConverter<FeaturesAttribute, String> {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(FeaturesAttribute attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.NOT_EXISTS_INFO, e.getMessage());
        }
    }

    @Override
    public FeaturesAttribute convertToEntityAttribute(String dbData) {
        try{
            return objectMapper.readValue(dbData , FeaturesAttribute.class);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.NOT_EXISTS_INFO, e.getMessage());
        }
    }
}
