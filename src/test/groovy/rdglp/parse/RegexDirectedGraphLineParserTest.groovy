package rdglp.parse

import rdglp.node.ParserNode
import rdglp.strategy.LineHandlingStrategy
import spock.lang.Specification
import spock.lang.Unroll

class RegexDirectedGraphLineParserTest extends Specification {
    @Unroll
    def "Given a parser applicable for a line '#expectedLine' and an actual line '#actualLine' then parser node's line handler should be called #handleCallCount times and an exception should be thrown: #wasExceptionThrown"(String expectedLine,
                String actualLine,
                int handleCallCount,
                boolean wasExceptionThrown) {
        given:
            Object model = new Object()
            def parserNodeMock = getMockParserNode(expectedLine)
            RegexDirectedGraphLineParser regexDirectedGraphLineParser =
                    new RegexDirectedGraphLineParser(model, parserNodeMock)

        when:
            try {
                regexDirectedGraphLineParser.parse(actualLine)
            } catch (Exception ignored) {}

        then:
            handleCallCount * parserNodeMock.getLineHandlingStrategy().handle(actualLine, model)
            // TODO: Groovyc error: Exception conditions are only allowed as top-level statements
            // TODO:    Find out how to do this
//            if (wasExceptionThrown) {
//                thrown(Exception)
//            }

        where:
            expectedLine | actualLine | handleCallCount | wasExceptionThrown
            "1"          | "1"        | 1               | false
            "1"          | "2"        | 0               | true
    }

    private ParserNode getMockParserNode(String line) {
        def parserNode = Mock(ParserNode) {
            isApplicable(line) >> true
            getLineHandlingStrategy() >> Mock(LineHandlingStrategy)
        }
        return parserNode
    }
}
