package rdglp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import groovy.lang.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigParser {
    private static ConfigParser INSTANCE;

    private ConfigParser() {
    }

    public static ConfigParser getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfigParser();
        }

        return INSTANCE;
    }

    public ParserConfig generateParserConfig(InputStream config) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, ParserNodeConfig.class);
        mapper.readValue(config, javaType);

        return ParserConfig.getInstance();
    }
}
