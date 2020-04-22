package ws.restful.model;

import entity.Peripheral;
import java.util.List;

public class RetrieveAllPeripheralsRsp {
    
    private List<Peripheral> peripherals;

    public RetrieveAllPeripheralsRsp() {
    }

    public RetrieveAllPeripheralsRsp(List<Peripheral> peripherals) {
        this.peripherals = peripherals;
    }

    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    public void setPeripherals(List<Peripheral> peripherals) {
        this.peripherals = peripherals;
    }
    
}
