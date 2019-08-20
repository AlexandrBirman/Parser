package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class RunetIdScruperStandart extends BaseScruper {
    private static String url = "https://runet-id.com/events/";

    @Override
    protected List<Event> getReferences(String content) throws IOException {
        List<Event> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.unit.span4.event");

        String buffer;

        for (int i = 0; i < elements.size(); i++) {
            event = new Event();

            event.setDate(elements.get(i).select("h3.date").text());

            

            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));
            event.setName(elements.get(i).select("h3.title").text());
            event.setLocation(elements.get(i).select("small.muted").text().split(",")[0].split(" ")[1]);




            data.add(event);
        }

        return data;
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences(null);
    }
}
