package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ITeventsScraperStandart extends BaseScruper {

    private  static String url = "https://it-events.com/";

    @Override
    protected List<Event> getReferences(String content) throws IOException {

        List<Event> data = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.event-list-item");
        Elements links = elements.select("a[href]"); // в этом месте создается два элемента одной ссылки
        for (int i = 0; i < elements.size(); i += 1) {
            //builder.setLength(0);
            event = new Event();

            //builder.append(elements.get(i).select("a[href]").attr("abs:href") + "\n");
            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

            //builder.append(elements.get(i).getElementsByClass("event-list-item__type").text() + "\n");
            event.setCategory(elements.get(i).getElementsByClass("event-list-item__type").text());

            //builder.append(elements.get(i).getElementsByClass("event-list-item__title").text() + "\n");
            event.setName(elements.get(i).getElementsByClass("event-list-item__title").text());

            //builder.append(elements.get(i).getElementsByClass("event-list-item__info").text());
            event.setLocation(elements.get(i).getElementsByClass("event-list-item__info").text());


            data.add(event);
        }

        return data;
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences(null);
    }


}
