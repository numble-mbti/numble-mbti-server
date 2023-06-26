package numble.mbti.domain.features.contstant;


import jakarta.persistence.ElementCollection;
import org.hibernate.query.sqm.tree.expression.Compatibility;

import java.util.List;

public class FeaturesAttribute {
    private String name;
    private Integer endangered_grade;

    @ElementCollection
    private List<String> contents;

    private Compatibility compatibility;

}



