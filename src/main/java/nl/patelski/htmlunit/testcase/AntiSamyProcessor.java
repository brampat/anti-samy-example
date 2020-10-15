package nl.patelski.htmlunit.testcase;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

import java.net.URL;

public class AntiSamyProcessor {

    private String POLICY_FILE_LOCATION = "antisamy-custom.xml";

    private AntiSamy antiSamy;
    private Policy policy;

    public void init() throws PolicyException {
        URL url = this.getClass().getClassLoader().getResource(POLICY_FILE_LOCATION);
        policy = Policy.getInstance(url);
        antiSamy = new AntiSamy();
    }

    public String filter(String dirtyHtml) throws PolicyException, ScanException {
        CleanResults cr = antiSamy.scan(dirtyHtml, policy);
        return cr.getCleanHTML();
    }


}
