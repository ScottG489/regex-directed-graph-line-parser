package rdglp.node;

import rdglp.strategy.LineHandlingStrategy;

import java.util.Set;

public interface ParserNode {
    public abstract boolean isApplicable(String line);

    public abstract LineHandlingStrategy getLineHandlingStrategy();

    public abstract Set<ParserNode> getNextNodes();

    public abstract void setNextNodes();
}
