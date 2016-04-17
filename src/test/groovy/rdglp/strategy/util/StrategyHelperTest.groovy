package rdglp.strategy.util

import spock.lang.Specification
import spock.lang.Unroll

import java.util.regex.Pattern

class StrategyHelperTest extends Specification {
    @Unroll
    def "Given a line '#line' and pattern of '#line' then the returned data should be '#line'"(String line, String expectedData) {
        given:
            Pattern pattern = Pattern.compile(line)

        when:
            String data = StrategyHelper.extractDataFromLine(line, pattern, 0)

        then:
            data.equals(expectedData)

        where:
            line | expectedData
            "a"  | "a"
    }
}
