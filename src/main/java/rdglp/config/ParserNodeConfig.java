package rdglp.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = ParserNodeConfigDeserializer.class)
public class ParserNodeConfig {
    private String name;
    private String applicabilityPattern;
    private String lineHandlingStrategy;
    private List<String> possibleNextNodes;
}
