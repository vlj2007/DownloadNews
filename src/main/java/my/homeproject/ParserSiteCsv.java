package my.homeproject;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;

public class ParserSiteCsv {

    public static void getParser(){
        final String domainSiteNews = "https://ngs.ru";
        final String url = "https://ngs.ru/text/";

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("ngs.csv"))) {
            csvWriter.writeNext(new String[]{"Rubrics", "Title", "Date", "Views", "Comments", "Links"});

            final Document doc = Jsoup.connect(url).get();

            Elements productElements = doc.select("div.eQ4k7");

            for (Element productElement : productElements) {
                String rubrics = productElement.select("a[href]").attr("title");
                String title = productElement.select("h2").text();
                String date = productElement.select("div.Hiu4B.vx3Rq").text();
                String views = productElement.select("div.\\+N8hV").text();
                String comments = productElement.select("div.e0SBr > a").text();
                String linkWithoutDomain = productElement.select("h2 > a").attr("href");
                String links = domainSiteNews + linkWithoutDomain;

                csvWriter.writeNext(new String[]{rubrics, title, date, views, comments, links});
            }
            System.out.println("Data successfully exported to CSV");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
