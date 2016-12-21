package codewizards.com.ua.picviewer.server_mock;

import android.content.Context;
import android.content.res.Resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import codewizards.com.ua.picviewer.R;

/**
 * Created by Интернет on 21.12.2016.
 */

public class DataProvider {
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
