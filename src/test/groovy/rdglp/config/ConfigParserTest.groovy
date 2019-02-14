package rdglp.config

import rdglp.node.ParserNode
import spock.lang.Specification

import rdglp.strategy.EmptyHandlingStrategy

class ConfigParserTest extends Specification {
    def "A parsed"() {
        given: "ParserNodes parsed from configuration"
            ParserNode diffGitParserNode = getParserConfig().get("diffGitNode")
            ParserNode newFileModeParserNode = getParserConfig().get("newFileModeNode")

        expect: "That the nodes behave appropriately"
            diffGitParserNode instanceof ParserNode
            diffGitParserNode.isApplicable("diff --git a/foo b/bar")
            diffGitParserNode.getLineHandlingStrategy() instanceof EmptyHandlingStrategy
            // TODO: For some reason this has a different instance of newFileModeParserNode
            // TODO:    but I'm not sure why.
            // XXX: Find out why there's a different instance in here.
//            diffGitParserNode.getNextNodes().contains(newFileModeParserNode)
    }

    private static Map<String, ParserNode> getParserConfig() {
        InputStream config = getTestResourceText("diff_test.json")
        return ConfigParser.getInstance().generateParserConfig(config)
    }

    private static InputStream getTestResourceText(String fileName) {
        return ParserConfig.getResourceAsStream(fileName)
    }
}
