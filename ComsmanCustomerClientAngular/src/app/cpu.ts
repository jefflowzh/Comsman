import { ComputerPart } from './computer-part'

export class CPU extends ComputerPart {

    manufacturer: string;
    coreCount: number;
    TDP: number;
    socket: string;
    hasIntergratedGraphics: boolean;
    includesCpuCooler: boolean;

    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: Boolean,
        manufacturer?: string, coreCount?: number, TDP?: number, socket?: string, hasIntergratedGraphics?: boolean,includesCpuCooler?: boolean) {
        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.coreCount = coreCount;
        this.TDP = TDP;
        this.socket = socket;
        this.hasIntergratedGraphics = hasIntergratedGraphics;
        this.includesCpuCooler = includesCpuCooler;
        
    }

}