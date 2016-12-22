package codewizards.com.ua.picviewer.data_workers.old;

import com.google.gson.Gson;

import java.util.List;

import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.model.Response;

/**
 * Created by Интернет on 21.12.2016.
 */
@Deprecated
public class GoodJSONParser {
    public static List<Good> parseStringToList(String data) {
        Gson gson = new Gson();
        Response response = gson.fromJson(data, Response.class);
        return response.getListOfGoods();
    }
}
