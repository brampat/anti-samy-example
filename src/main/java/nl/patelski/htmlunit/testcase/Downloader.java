package nl.patelski.htmlunit.testcase;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
public class Downloader {

    public String download(URI site) throws IOException {
        return download(site.toURL());
    }

    public String download(URL site) {

        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();

        try (final WebClient webClient = new WebClient()) {

            webClient.getOptions().setPopupBlockerEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            WebRequest request = new WebRequest(site);
            request.setCharset(Charset.forName("UTF-8"));

            final HtmlPage page = webClient.getPage(request);
//            final HtmlPage page = webClient.getPage(site);

//            barrayXml = page.asXml().getBytes(Charset.forName("UTF-8"));
//            Files.write(Paths.get(outputPath + File.separator + fileNameTruncated + TXT), barrayXml );
//            return page.getTextContent();
            return page.asXml();
//            return String.valueOf(page.asXml().getBytes(Charset.forName("UTF-8")));
//            return String.valueOf(page.asXml().getBytes(page.getCharset()));
        } catch (MalformedURLException e) {
            log.error(String.format("Unable to download due to Malformed URL Exception for %s", site.toString()));
        } catch (IOException e) {
            log.error(String.format("IOException when downloading %s", site.toString()));
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException ioe) {
                log.error(String.format("IOException when closing connection for %s", site.toString()));
            }
        }
        return result.toString();
    }

}