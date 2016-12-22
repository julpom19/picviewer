package codewizards.com.ua.picviewer.data_workers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.model.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Интернет on 21.12.2016.
 */

public class DataContainer implements DataProvider {
    private static DataContainer dataContainer = new DataContainer();
    private DataContainer() {}
    public static DataContainer getInstance() {
        return dataContainer;
    }
    private boolean downloadStart;

    List<DataObserver> observers = new ArrayList<>();
    private List<Good> listOfGoods;

    public List<Good> getListOfGoods() {
        Log.d("myTag","getListOfGoods");
        if(listOfGoods == null && !downloadStart) {
            downloadListOfGoods();
            Log.d("myTag","Download");
        }
        return listOfGoods;
    }

//    private void downloadListOfGoods(Context context) {
////        String jsonData = DataProvider.getJSONData(context);
////        listOfGoods = GoodJSONParser.parseStringToList(jsonData);
//        listOfGoods =
//    }

    @Override
    public void registerObserver(DataObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(DataObserver observer: observers) {
            observer.update();
        }
    }

    private void downloadListOfGoods() {
        downloadStart = true;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.HEROKU_APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HerokuService service = retrofit.create(HerokuService.class);
        Call<Response> call = service.getResponse();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {
                    listOfGoods = response.body().getListOfGoods();
                    notifyObservers();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
