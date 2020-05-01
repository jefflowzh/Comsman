import { ComputerPart } from './computer-part'

export class Gpu extends ComputerPart {


    manufacturer: string;
    chipset : string;
    Interface : string;
    length :number;
    TDP :number;
    expansionSlotWidth :number;
    externalPower : string;
    memory :number;
    memoryType : string;


    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: Boolean,
        manufacturer?: string, 
        chipset ?: string,
        Interface? : string,
        length? :number,
        TDP? :number,
        expansionSlotWidth? :number,
        externalPower? : string,
        memory? :number,
        memoryType? : string) {

        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.chipset = chipset
        this.Interface = Interface
        this.length = length
        this.TDP = TDP
        this.expansionSlotWidth = expansionSlotWidth
        this.externalPower = externalPower
        this.memory = memory
        this.memoryType = memoryType
    }

}