package rdglp.parse

import rdglp.node.ParserNode
import rdglp.strategy.LineHandlingStrategy

class RegexDirectedGraphLineParser<ModelType> implements LineParser {
    protected ModelType model
    protected ParserNode firstNode

    RegexDirectedGraphLineParser(ModelType model, ParserNode firstNode) {
        this.model = model
        this.firstNode = firstNode
    }

    @Override
    ModelType parse(String parsableLines) {
        Iterator<String> lineIter = parsableLines.readLines().iterator()
        traverseDirectedNodeGraph(lineIter, [firstNode].toSet())
        return model
    }

    void traverseDirectedNodeGraph(Iterator<String> lineIter, Set<ParserNode> nextNodes) {
        // TODO: Find a more succinct way of doing this
        if (!lineIter.hasNext() || (nextNodes == null || nextNodes.isEmpty())) return
        String line = lineIter.next()
        for (ParserNode node : nextNodes) {
            if (node.isApplicable(line)) {
                LineHandlingStrategy lineHandler = node.getLineHandlingStrategy()
                lineHandler.handle(line, model)
                traverseDirectedNodeGraph(lineIter, node.getNextNodes())
                return
            }
        }
        // TODO: Should we use a custom exception?
        throw new Exception("Malformed patch. Line was not expected: ${line}")
    }
}