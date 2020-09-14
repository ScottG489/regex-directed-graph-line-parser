package rdglp.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import rdglp.node.GenericParserNode;
import rdglp.node.ParserNode;
import rdglp.strategy.LineHandlingStrategy;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserNodeConfigDeserializer extends JsonDeserializer<ParserNodeConfig> {
    @Override
    public ParserNodeConfig deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String parserNodeConfigName = node.get("name").asText();
        Class<?> clazz;
        LineHandlingStrategy lineHandlingStrategy;
        try {
            clazz = Class.forName(node.get("lineHandlingStrategy").asText());
            lineHandlingStrategy = (LineHandlingStrategy) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        String applicabilityPattern = node.get("applicabilityPattern").asText();


        List<String> possibleNextNodeNames = getNodeAsList(node.get("possibleNextNodes"));
        Set<ParserNode> possibleNextNodes = getPossibleNextNodes(possibleNextNodeNames);

        ParserNode parserNode = new GenericParserNode(applicabilityPattern, lineHandlingStrategy, possibleNextNodes);

        ParserConfig parserConfig = ParserConfig.getInstance();
        parserConfig.put(parserNodeConfigName, parserNode);

        // TODO: This is kinda weird how we're 'done' once parsing (don't need to
        // TODO:    handle returned parsed object)
        return null;
    }

    private List<String> getNodeAsList(JsonNode node) throws IOException {
        ObjectReader reader = new ObjectMapper().readerFor(new TypeReference<List<String>>() { });
        return reader.readValue(node);
    }

    private static Set<ParserNode> getPossibleNextNodes(List<String> possibleNextNodeNames) {
        Set<ParserNode> possibleNextNodes = new HashSet<ParserNode>();
        ParserConfig parserConfig = ParserConfig.getInstance();
        for (String nodeName : possibleNextNodeNames) {
            // TODO: Throw error here if nodeName isn't in parserConfig
            possibleNextNodes.add(parserConfig.get(nodeName));
        }


        return possibleNextNodes;
    }

}
