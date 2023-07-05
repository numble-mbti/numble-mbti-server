package numble.mbti.domain.features.contstant;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeaturesAttribute {
    private List<String> contents;
    private List<String> sub_contents;
    private Compatibility compatibility;

}



