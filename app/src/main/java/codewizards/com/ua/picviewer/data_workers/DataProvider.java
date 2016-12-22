package codewizards.com.ua.picviewer.data_workers;

/**
 * Created by Интернет on 22.12.2016.
 */

public interface DataProvider {
    void registerObserver(DataObserver observer);
    void notifyObservers();
    void unregisterObserver(DataObserver observer);
}
