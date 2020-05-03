import { ComputerPart } from './computer-part'

export class ComputerCase extends ComputerPart {

    manufacturer: string;
    type: string;
    colour: string;
    sidePanelView: string;
    motherBoardFormFactor: string[];
    fullHeightExpansionSlot: number;
    maxVideoCardLength: number;
    topFanSupport: number;
    frontFanSupport: number;
    rearFanSupport: number;

    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
        manufacturer?: string, type?: string, colours?: string, sidePanelView?: string, motherBoardFormFactor?: string[],
        fullHeightExpansionSlot?: number, maxVideoCardLength?: number, topFanSupport?: number, frontFanSupport?: number,
        rearFanSupport?: number) {
        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.type = type;
        this.colour = colours;
        this.sidePanelView = sidePanelView;
        this.motherBoardFormFactor = motherBoardFormFactor;
        this.fullHeightExpansionSlot = fullHeightExpansionSlot;
        this.maxVideoCardLength = maxVideoCardLength;
        this.topFanSupport = topFanSupport;
        this.frontFanSupport = frontFanSupport;
        this.rearFanSupport = rearFanSupport
    }

}
