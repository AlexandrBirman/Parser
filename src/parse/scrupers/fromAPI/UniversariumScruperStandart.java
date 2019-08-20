package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.scrupers.BaseScruper;
import parse.scrupers.Event;
import util.StringsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// не робит
public class UniversariumScruperStandart extends BaseScruper {

    private static String url = "https://universarium.org/mapi/fcourses";
    private static String buildURL = "https://universarium.org/course/";


    @Override
    protected List<Event> getReferences(String content) throws IOException {
        List<Event> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("response");
        StringBuilder builder = new StringBuilder();
        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                //builder.setLength(0);
                event = new Event();

                //builder.append(StringsUtil.deleteCommos(buildURL + objNode.get("id").toString()) + "\n");
                event.setUrl(StringsUtil.deleteCommos(buildURL + objNode.get("id").toString()));

                //builder.append(StringsUtil.deleteCommos(objNode.get("category").toString()) + "\n");
                event.setCategory(StringsUtil.deleteCommos(objNode.get("category").toString()));

                //builder.append("Курс: " + StringsUtil.deleteCommos(objNode.get("title").toString()) + "\n");
                event.setName("Курс: " + StringsUtil.deleteCommos(objNode.get("title").toString()));

                //builder.append((objNode.get("purpose").toString() + "\n"));
                response.add(event);
            }
        }

        return  response;
    }

    @Override
    public List<Event> getData() throws IOException {
        return getReferences(getJsonString(url));
    }
}
