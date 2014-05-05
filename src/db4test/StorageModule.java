package db4test;

/**
 * Created by blaed on 5/3/14.
 */
public class StorageModule extends O2Holder {

    private long storageID;
    private double longitude;
    private double latitude;

    public StorageModule(long storageID, double longitude, double latitude) {
        this.storageID = storageID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getStorageID() {
        return storageID;
    }

    public void setStorageID(long storageID) {
        this.storageID = storageID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
