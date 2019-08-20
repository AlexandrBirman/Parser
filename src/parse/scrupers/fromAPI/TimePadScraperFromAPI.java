package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;
import util.StringsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimePadScraperFromAPI extends BaseScruper {

    private int limit = 100;

    private String url = "https://api.timepad.ru/v1/events.json?limit=" + limit + "&category_ids=217" + "&category_ids=452";


    @Override
    public List<Event> getData() throws IOException {
        return getReferences(getJsonString(url));
    }

    @Override
    public List<Event> getReferences(String content) throws IOException {
        List<Event> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("values");
        StringBuilder builder = new StringBuilder();

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                builder.setLength(0);
                event = new Event();
                //builder.append(objNode.get("categories").get("name").toString() + "\n");
                event.setUrl(StringsUtil.deleteApostrophe(StringsUtil.deleteCommos(objNode.get("url").toString())));
                event.setName(StringsUtil.deleteApostrophe(StringsUtil.deleteCommos(objNode.get("name").toString())));
                //builder.append(objNode.get("area").get("name").toString());
                response.add(event);
            }
        }

        return  response;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}


