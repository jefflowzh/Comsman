package ejb.session.stateless;

import entity.Peripheral;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PeripheralSessionBeanLocal {

    public Long createNewPeripheral(Peripheral newPeripheral);

    public List<Peripheral> retrieveAllPeripherals();
    
}
