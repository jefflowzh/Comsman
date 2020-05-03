package ejb.session.stateless;

import entity.ComputerSet;
import entity.PreBuiltComputerSetModel;
import java.util.List;
import javax.ejb.Local;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetTierAlreadyExistsException;
import util.exception.IncompatiblePartException;
import util.exception.IncompleteComputerSetException;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Local
public interface PreBuiltComputerSetModelSessionBeanLocal {

    public Long createNewPreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws ComputerSetTierAlreadyExistsException;

    public List<PreBuiltComputerSetModel> retrieveAllPreBuiltComputerSetModels();

    public void updatePreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws PreBuiltComputerSetModelNotFoundException;

    public boolean compatibilityCheck(PreBuiltComputerSetModel model, Long current) throws IncompatiblePartException, ComputerPartNotFoundException;

    public boolean finalComputerSetCheck(PreBuiltComputerSetModel model) throws IncompleteComputerSetException;
    
}