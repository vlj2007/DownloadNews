package my.homeproject;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class ParserSiteCsv {

    private static final String DOMAIN_SITE_NEWS = "https://ngs.ru";
    private static final String URL = "https://ngs.ru/text/";
    private static final String CSV_FILE_PATH = "ngs_novosibirks.csv";

    public static void getParser() {

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
            csvWriter.writeNext(new String[]{"Rubrics", "Title", "Date", "Views", "Comments", "Links"});
            Document doc = Jsoup.connect(URL).get();
            Elements productElements = doc.select("div.eQ4k7");

            for (Element productElement : productElements) {
                String[] productData = extractProductData(productElement);
                csvWriter.writeNext(productData);
            }
            System.out.println("Data successfully exported to CSV");
        } catch (IOException e) {
            System.err.println("Error while processing the data: " + e.getMessage());
        }
    }

    private static String[] extractProductData(Element productElement) {
        String rubrics = productElement.select("a[href]").attr("title");
        String title = productElement.select("h2").text();
        String date = productElement.select("div.Hiu4B.vx3Rq").text();
        String views = productElement.select("div.\\+N8hV").text();
        String comments = productElement.select("div.e0SBr > a").text();
        String linkWithoutDomain = productElement.select("h2 > a").attr("href");
        String links = DOMAIN_SITE_NEWS + linkWithoutDomain;

        return new String[]{rubrics, title, date, views, comments, links};
    }

}
