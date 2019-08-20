package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LeaderIdScruperStandart extends BaseScruper {
    private static String url = "https://leader-id.ru/events/";

    @Override
    protected List<Event> getReferences(String content) throws IOException { // пока только одна страница парситься
        List<Event> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.col-lg-4.col-md-6.grid__item");
        //Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
            event = new Event();
            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));
             data.add(event);
        }

        return data;
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences(null);
    }
}
