import { ComputerPart } from './computer-part'

export class PowerSupply extends ComputerPart {


    manufacturer: string;
    formFactor: string;
    efficiency: string;
    wattage: number;
    modularity: string;
    SATAConnectors: number;
    PCIe6plus2: number;



    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
        manufacturer?: string,
        formFactor?: string,
        efficiency?: string,
        wattage?: number,
        modularity?: string,
        SATAConnectors?: number,
        PCIe6plus2?: number) {

        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
        this.SATAConnectors = SATAConnectors;
        this.PCIe6plus2 = PCIe6plus2;

    }
}