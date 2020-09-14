package rdglp.parse;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import rdglp.node.ParserNode;
import rdglp.strategy.LineHandlingStrategy;

import java.awt.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class RegexDirectedGraphLineParser<ModelType> implements LineParser {
    public RegexDirectedGraphLineParser(ModelType model, ParserNode firstNode) {
        this.model = model;
        this.firstNode = firstNode;
    }

    @Override
    public ModelType parse(String parsableLines) {
        BufferedReader reader = new BufferedReader(new StringReader(parsableLines));
        Iterator<String> lineIter = reader.lines().collect(Collectors.toList()).iterator();
//        Iterator<String> lineIter = StringGroovyMethods.readLines(parsableLines).iterator();
        traverseDirectedNodeGraph(lineIter, DefaultGroovyMethods.toSet(new ArrayList<>(Arrays.asList(firstNode))));
        return model;
    }

    public void traverseDirectedNodeGraph(Iterator<String> lineIter, Set<ParserNode> nextNodes) {
        // TODO: Find a more succinct way of doing this
        if (!lineIter.hasNext() || (nextNodes == null || nextNodes.isEmpty())) return;

        final String line = lineIter.next();
        for (ParserNode node : nextNodes) {
            if (node.isApplicable(line)) {
                LineHandlingStrategy lineHandler = node.getLineHandlingStrategy();
                lineHandler.handle(line, model);
                traverseDirectedNodeGraph(lineIter, node.getNextNodes());
                return;

            }

        }

        // TODO: Should we use a custom exception?
        throw new RuntimeException("Malformed patch. Line was not expected: " + line);
    }

    protected ModelType model;
    protected ParserNode firstNode;
}
