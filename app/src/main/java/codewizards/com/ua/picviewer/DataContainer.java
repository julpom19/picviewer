package codewizards.com.ua.picviewer;

import android.content.Context;
import android.util.Log;

import java.util.List;

import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.model.GoodJSONParser;
import codewizards.com.ua.picviewer.server_mock.DataProvider;

/**
 * Created by Интернет on 21.12.2016.
 */

public class DataContainer {
    private static DataContainer dataContainer = new DataContainer();
    private List<Good> listOfGoods;
    private DataContainer() {}
    public static DataContainer getInstance() {
        return dataContainer;
    }

    public List<Good> getListOfGoods(Context context) {
        if(listOfGoods == null) {
            downloadListOfGoods(context);
        }
        return listOfGoods;
    }

    private void downloadListOfGoods(Context context) {
        String jsonData = DataProvider.getJSONData(context);
        listOfGoods = GoodJSONParser.parseStringToList(jsonData);
    }
}
