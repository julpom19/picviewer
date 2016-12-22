package codewizards.com.ua.picviewer.model;

import java.util.List;

/**
 * Created by Интернет on 21.12.2016.
 */

public class Response {
    private boolean isSuccessful;
    private List<Good> images;

    public List<Good> getListOfGoods() {
        return images;
    }

    public void setListOfGoods(List<Good> images) {
        this.images = images;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
