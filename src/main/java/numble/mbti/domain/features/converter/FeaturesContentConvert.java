package numble.mbti.domain.features.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import numble.mbti.domain.features.contstant.FeaturesAttribute;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FeaturesContentConvert implements AttributeConverter<FeaturesAttribute, String> {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(FeaturesAttribute attribute) {
       try{
           return objectMapper.writeValueAsString(attribute);
       }catch (Exception e){
           return  "";
       }
    }

    @Override
    public FeaturesAttribute convertToEntityAttribute(String dbData) {
        try{
            return objectMapper.readValue(dbData , FeaturesAttribute.class);
        }catch(Exception e){
            return null;
        }
    }
}
