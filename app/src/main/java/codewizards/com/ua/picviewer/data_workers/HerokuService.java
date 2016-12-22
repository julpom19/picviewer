package codewizards.com.ua.picviewer.data_workers;

import java.util.List;

import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Интернет on 22.12.2016.
 */

public interface HerokuService {
    @GET("/images")
    Call<Response> getResponse();
}
