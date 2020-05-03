/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.PreBuiltComputerSetModel;
import java.util.List;

/**
 *
 * @author jeffl
 */
public class RetrievePreBuiltComputerModelsRsp {
    
    List<PreBuiltComputerSetModel> preBuiltComputerSetModels;

    public RetrievePreBuiltComputerModelsRsp() {
    }

    public RetrievePreBuiltComputerModelsRsp(List<PreBuiltComputerSetModel> preBuiltComputerSetModels) {
        this.preBuiltComputerSetModels = preBuiltComputerSetModels;
    }

    public List<PreBuiltComputerSetModel> getPreBuiltComputerSetModels() {
        return preBuiltComputerSetModels;
    }

    public void setPreBuiltComputerSetModels(List<PreBuiltComputerSetModel> preBuiltComputerSetModels) {
        this.preBuiltComputerSetModels = preBuiltComputerSetModels;
    }

}
