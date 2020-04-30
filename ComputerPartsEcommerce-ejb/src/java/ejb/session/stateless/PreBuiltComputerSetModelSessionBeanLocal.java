package ejb.session.stateless;

import entity.PreBuiltComputerSetModel;
import java.util.List;
import javax.ejb.Local;
import util.exception.ComputerSetTierAlreadyExistsException;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Local
public interface PreBuiltComputerSetModelSessionBeanLocal {

    public Long createNewPreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws ComputerSetTierAlreadyExistsException;

    public List<PreBuiltComputerSetModel> retrieveAllPreBuiltComputerSetModels();

    public void updatePreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws PreBuiltComputerSetModelNotFoundException;
    
}
