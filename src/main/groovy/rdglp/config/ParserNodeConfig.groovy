package rdglp.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = ParserNodeConfigDeserializer.class)
// Dummy class so we can tie a custom deserializer to something.
// Along with being a dummy class this defines the schema for a valid config
public class ParserNodeConfig {
    private String name;
    private String applicabilityPattern;
    private String lineHandlingStrategy;
    private List<String> possibleNextNodes;
}
