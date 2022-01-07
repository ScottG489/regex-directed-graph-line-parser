package rdglp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigParser {
    public ParserConfig generateParserConfig(InputStream config) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, ParserNodeConfig.class);
        mapper.readValue(config, javaType);

        return ParserConfig.getInstance();
    }
}
