package rdglp.parse;

public interface LineParser<ModelType> {
    public abstract ModelType parse(String parsableLines);
}
