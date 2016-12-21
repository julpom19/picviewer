package codewizards.com.ua.picviewer.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Интернет on 21.12.2016.
 */

public class GoodJSONParser {
    public static List<Good> parseStringToList(String data) {
        Gson gson = new Gson();
        Response response = gson.fromJson(data, Response.class);
        return response.getListOfGoods();
    }
}
