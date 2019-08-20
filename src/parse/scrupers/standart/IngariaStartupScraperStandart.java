package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngariaStartupScraperStandart extends BaseScruper {

    private static String url = "https://ingria-startup.ru/events/";


    @Override
    protected List<Event> getReferences(String content) throws IOException {
        List<Event> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.event-left");
        //Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")) + elements.get(i).text());
            //builder.setLength(0);
            event = new Event();

            //builder.append(elements.get(i).select("a[href]").attr("abs:href") + "\n");
            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

            //builder.append(elements.get(i).select("div.title").text() + "\n");
            event.setName(elements.get(i).select("div.title").text());

            //builder.append(elements.get(i).select("div.date").text());
            event.setDate(elements.get(i).select("div.date").text());

            data.add(event);
        }

        return data;
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences(null);
    }
}
