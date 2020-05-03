import { ComputerPart } from './computer-part';

export class CPUAirCooler extends ComputerPart {

    manufacturer: string;
    colour: string;
    noiseLevel: number;
    height: number;
    CPUChipCompatibility: string[];

    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
        manufacturer?: string,
        color?: string,
        noiseLevel?: number,
        height?: number,
        CPUChipCompatibility?: string[]
        ) {

        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.colour = color;
        this.noiseLevel = noiseLevel;
        this.height = height
        this.CPUChipCompatibility = CPUChipCompatibility;
        
    }


}
