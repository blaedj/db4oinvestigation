package db4test;

/**
 * Created by blaed on 5/3/14.
 */
public class Location extends O2Holder {

    public Location(long locationID, double latitude, double longitude, ModuleType moduleType) {
        this.locationID = locationID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moduleType = moduleType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }


    public String toString(){
        return locationID + "/" + moduleType + "/Lat:" + latitude + "/Long:" + longitude+ "/" +moduleType;
    }

    public long getLocationID() {
        return locationID;
    }

    public void setLocationID(long locationID) {
        this.locationID = locationID;
    }


    public enum ModuleType  {
        Hygiene,
        Feeding,
        Medical,
        Sleeping}

    private long locationID;
    private double latitude;
    private double longitude;
    private ModuleType moduleType;

}
