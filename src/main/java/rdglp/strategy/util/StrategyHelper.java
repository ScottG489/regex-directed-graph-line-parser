package rdglp.strategy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrategyHelper {
    public static String extractDataFromLine(String line, Pattern lineExpression, int groupNumber) {
        Matcher m = lineExpression.matcher(line);
        m.find();
        return m.group(groupNumber);
    }

}
