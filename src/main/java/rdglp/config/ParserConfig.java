package rdglp.config;

import groovy.lang.Singleton;
import rdglp.node.ParserNode;

import java.util.HashMap;

@Singleton
public class ParserConfig extends HashMap<String, ParserNode> {
    private static ParserConfig INSTANCE;

    private ParserConfig() {
    }

    public static ParserConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParserConfig();
        }

        return INSTANCE;
    }

}
