package codewizards.com.ua.picviewer.data_workers.old;

import android.content.Context;
import android.content.res.Resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.model.Good;


import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.model.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Интернет on 21.12.2016.
 */
@Deprecated
public class DataFromFileProvider {

    @Deprecated
    public static String getJSONData(Context context) {
        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.data);
        String result = null;
        try {
            result = readStringFromIS(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Deprecated
    private static String readStringFromIS(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = inputStream.read();
        while (i != -1) {
            baos.write(i);
            i = inputStream.read();
        }
        return baos.toString();
    }

}
