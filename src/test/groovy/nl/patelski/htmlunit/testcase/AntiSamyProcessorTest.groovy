package nl.patelski.htmlunit.testcase

import spock.lang.Specification
import spock.lang.Unroll

class AntiSamyProcessorTest extends Specification {

    AntiSamyProcessor subject = new AntiSamyProcessor()

    def setup() {
        subject.POLICY_FILE_LOCATION = "antisamy-custom.xml"
        subject.init()
    }

    @Unroll
    void "When cleaning #file the output contains the string #expected "() {
        given:
        subject
        File input = new File("src/test/resources/${file}")

        when:
        String result = subject.filter(input.text)

        then:
        result.contains(expected)

        where:
        file                                                        ||  expected
        "thehackernews.com_ai-training_unfiltered.html"             ||  "<meta content=\"2020-07-27T01:35:00-07:00\" itemprop=\"datePublished\" />"
        "thehackernews.com_ai-training_unfiltered.html"             ||  "<meta content=\"The Hacker News\" itemprop=\"name\" />"
        "thehackernews.com_ai-training_unfiltered.html"             ||  "<div itemprop=\"author\">"
        "thehackernews.com_ai-training_unfiltered.html"             ||  "content=\"Learn Machine Learning and AI – Online Training Program @ 93% OFF\" itemprop=\"headline\" />"
    }

}
