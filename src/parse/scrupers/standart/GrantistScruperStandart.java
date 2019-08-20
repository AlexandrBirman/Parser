package parse.scrupers.standart;

import parse.scrupers.BaseScruper;
import parse.scrupers.Event;

import java.io.IOException;
import java.util.List;
public class GrantistScruperStandart extends BaseScruper {
    private static String url = "http://grantist.com/page/126/";

    @Override
    protected List<Event> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences("content-area");
    }
}
