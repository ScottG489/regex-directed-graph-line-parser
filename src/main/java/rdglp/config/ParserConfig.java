package rdglp.config;

import rdglp.node.ParserNode;

import java.util.HashMap;

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
