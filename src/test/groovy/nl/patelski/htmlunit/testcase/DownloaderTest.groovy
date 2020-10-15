package nl.patelski.htmlunit.testcase

import spock.lang.Specification
import spock.lang.Unroll

class DownloaderTest extends Specification {

    Downloader subject = new Downloader()

    @Unroll
    void "When downloading #url the output contains the string #expected "() {
        given:
        subject
        URL uri = new URL(url)

        when:
        String result = subject.download(uri)
        File out = new File("download_unfiltered.html")
        out << result

        then:
        result.contains(expected)

        where:
        url                                                                         ||  expected
//        "https://thehackernews.com/2019/07/android-permission-bypass.html"          ||  "Over 1,300 Android Apps Caught Collecting Data Even If You Deny Permissions"
        "https://thehackernews.com/2020/07/artificial-intelligence-training.html"   ||  "<meta content=\"Learn Machine Learning and AI – Online Training Program @ 93% OFF\" itemprop=\"headline\"/>"
        "https://thehackernews.com/2020/10/apple-security.html"                     ||  "<meta content=\"55 New Security Flaws Reported in Apple Software and Services\" itemprop=\"headline\"/>"
//        "https://darknetdiaries.com/transcript/32/"                                 ||  "A stolen credit card can be worth hundreds of dollars"
    }

}
