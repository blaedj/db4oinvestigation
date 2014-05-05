package db4test;

import java.util.Date;

/**
 * Created by blaed on 5/3/14.
 */
public class OxygenTank {

    private double percentFull;
    private Date lastInspected;
    private long tankID;

    public OxygenTank(long tankID,double percentFull, Date lastInspected){
        this.tankID = tankID;
        this.percentFull = (percentFull <= 100.0 && percentFull >= 0.0) ? percentFull : 0.0;
        this.lastInspected = lastInspected;
    };

    public double getPercentFull() {
        return percentFull;
    }

    public void setPercentFull(double percentFull) throws Exception {
        if(percentFull <= 100.0 && percentFull >= 0.0) {
            this.percentFull = percentFull;
        } else {
            throw new Exception("Invalid percent full value, "+
                    "expected double between 0.0 and 100.0, got" +
                    percentFull);
        }
    }

    public Date getLastInspected(){
        return lastInspected;

    }

    public void setLastInspected(Date d){
        lastInspected = d;
    }

    public long getTankID() {
        return tankID;
    }

    public void setTankID(long tankID) {
        this.tankID = tankID;
    }

    public String toString(){
        return tankID + "/" +lastInspected + "/" + percentFull +"% filled";

    }

}
