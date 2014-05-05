package db4test;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by blaed on 5/3/14.
 */
public class DBDriver {

    private static final String DB4OFILENAME = "testfilename.db";

    public static void main(String[] args) {
        long queryStartTime, queryEndTime;

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
        try {
            populateData(db);
            //create a Native Query
            queryStartTime = System.nanoTime();
            List<Location> results = db.query(new Predicate<Location>() {
                public boolean match(Location loc) {
                    List<OxygenTank> tankList = loc.getTanks();
                    for (OxygenTank t : tankList) {
                        if (t.getPercentFull() <= 50.0
                                && isBeforeDays(7, t.getLastInspected())) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            queryEndTime = System.nanoTime();
            listResult(results, queryEndTime - queryStartTime);
        } finally {
            db.close();
        }
    }

    private static boolean isBeforeDays(int days, Date aDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return aDate.compareTo(calendar.getTime()) < 0;
    }


    private static void populateData(ObjectContainer db) {

        OxygenTank ox0 = new OxygenTank(15, 87.5, to_date("17-FEB-2014"));
        OxygenTank ox1 = new OxygenTank(18, 100, to_date("02-FEB-2014"));
        OxygenTank ox2 = new OxygenTank(53, 12, to_date("25-FEB-2014"));
        OxygenTank ox3 = new OxygenTank(85, 0.1, to_date("15-FEB-2014"));
        OxygenTank ox4 = new OxygenTank(74, 80.5, to_date("25-FEB-2014"));
        OxygenTank ox5 = new OxygenTank(19, 10, to_date("05-FEB-2014"));
        OxygenTank ox6 = new OxygenTank(20, 18, to_date("05-FEB-2014"));
        OxygenTank ox7 = new OxygenTank(21, 55, to_date("25-FEB-2014"));
        OxygenTank ox8 = new OxygenTank(22, 50, to_date("25-FEB-2014"));
        OxygenTank ox9 = new OxygenTank(23, 89, to_date("25-JAN-2014"));

        StorageModule stm0 = new StorageModule(54, 45.0, 44.0);
        StorageModule stm1 = new StorageModule(18, 63.5, 36.5);
        StorageModule stm2 = new StorageModule(57, 55.0, 12.0);
        StorageModule stm3 = new StorageModule(12, 21.0, 18.0);
        StorageModule stm4 = new StorageModule(89, 98.0, 3.0);

        Location loc0 = new Location(12, 12.0, 21.0, Location.ModuleType.Medical);
        Location loc1 = new Location(13, 31.0, 1.0, Location.ModuleType.Sleeping);
        Location loc2 = new Location(14, 51.0, 15.0, Location.ModuleType.Feeding);
        Location loc3 = new Location(15, 14.0, 41.0, Location.ModuleType.Hygiene);
        Location loc4 = new Location(16, 18.0, 81.0, Location.ModuleType.Medical);

        loc0.addTank(ox7);
        loc1.addTank(ox8);
        loc2.addTank(ox6);
        loc3.addTank(ox5);
        loc4.addTank(ox9);

        stm0.addTank(ox2);
        stm1.addTank(ox3);
        stm2.addTank(ox4);
        stm3.addTank(ox0);
        stm4.addTank(ox1);

        db.store(ox0);
        db.store(ox1);
        db.store(ox2);
        db.store(ox3);
        db.store(ox4);
        db.store(ox5);
        db.store(ox6);
        db.store(ox7);
        db.store(ox8);
        db.store(ox9);

        db.store(loc0);
        db.store(loc1);
        db.store(loc2);
        db.store(loc3);
        db.store(loc4);
    }

    public static Date to_date(String s) {
        return new Date(Date.parse(s));
    }

    public static void listResult(List<?> result, long elapsedTime) {
        System.out.println("Query elapsed time (in nanoseconds) = " + elapsedTime);
        System.out.println("Number of results: " + result.size());
        System.out.println("----Results:----");
        for (Object o : result) {
            System.out.println(o);
        }
    }

}
