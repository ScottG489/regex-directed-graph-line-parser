package rdglp.parse

interface LineParser<ModelType> {
    ModelType parse(String parsableLines)
}