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
    private boolean dataError;

    List<DataObserver> observers = new ArrayList<>();
    private List<Good> listOfGoods;

    public List<Good> getListOfGoods() {
        Log.d("myTag","getListOfGoods");
        //Скачиваем, если списка нет (то есть первое скачивание), при этом уже не должно происходить
        //скачивание. Так же список может быть == null, если произошла ошибка. В этом случае тоже
        //скачивать не нужно, только по просьбе пользователя, иначе зацикливание
        if(listOfGoods == null && !downloadStart && !dataError) {
            Log.d("myTag","Download");
            downloadListOfGoods();
        }
        return listOfGoods;
    }

    public void refreshData() {
        if(!downloadStart) {
            downloadListOfGoods();
        }
    }

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

    @Override
    public void unregisterObserver(DataObserver observer) {
        observers.remove(observer);
    }

    private void downloadListOfGoods() {
        Log.d("myTag", "Start of loading");
        dataError = false;
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
                } else {
                    dataError = true;
                    Log.d("myTag", "Data error - in onResponse");
                }
                downloadStart = false;
                Log.d("myTag", "Finish of loading");
                notifyObservers();

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dataError = true;
                downloadStart = false;
                Log.d("myTag", "Finish of loading");
                Log.d("myTag", "Data error - in onFailure");
                notifyObservers();
            }
        });
    }

    public boolean isDataError() {
        return dataError;
    }

    public void setDataError(boolean dataError) {
        this.dataError = dataError;
    }
}
