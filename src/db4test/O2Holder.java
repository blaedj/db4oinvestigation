package db4test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blaed on 5/3/14.
 */
public class O2Holder {
    List<OxygenTank> tanks = new ArrayList<OxygenTank>();

    public List<OxygenTank> getTanks() {
        return tanks;
    }
    public OxygenTank getTank(long tankID){
        for (int i = 0; i < tanks.size(); i++) {
            if(tanks.get(i).getTankID() == tankID ){
                return tanks.get(i);
            }
        }
        return null;
    }

    public void addTank(OxygenTank tank){
        tanks.add(tank);
    }
}
