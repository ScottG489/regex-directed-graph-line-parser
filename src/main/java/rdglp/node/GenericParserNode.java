package rdglp.node;

import org.codehaus.groovy.runtime.StringGroovyMethods;
import rdglp.strategy.LineHandlingStrategy;

import java.util.Set;
import java.util.regex.Pattern;

public class GenericParserNode implements ParserNode {
    public GenericParserNode(String applicabilityPattern, LineHandlingStrategy lineHandlingStrategy, Set<ParserNode> possibleNextNodes) {
        this.applicabilityPattern = Pattern.compile(applicabilityPattern);
        this.lineHandlingStrategy = lineHandlingStrategy;
        this.possibleNextNodes = possibleNextNodes;
    }

    @Override
    public boolean isApplicable(String line) {
        return StringGroovyMethods.matches(line, applicabilityPattern);
    }

    @Override
    public LineHandlingStrategy getLineHandlingStrategy() {
        return lineHandlingStrategy;
    }

    @Override
    public Set<ParserNode> getNextNodes() {
        return possibleNextNodes;
    }

    @Override
    public void setNextNodes() {

    }

    private Pattern applicabilityPattern;
    private LineHandlingStrategy lineHandlingStrategy;
    private Set<ParserNode> possibleNextNodes;
}
