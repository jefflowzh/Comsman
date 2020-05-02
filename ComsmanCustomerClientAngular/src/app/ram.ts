import { ComputerPart } from './computer-part'

export class RAM extends ComputerPart {


    manufacturer: string;
    speed: string;
    type: string;
    sticks: number;
    perStickGB: number;
    casLatency: number;



    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
        manufacturer?: string,
        speed?: string,
        type?: string,
        sticks?: number,
        perStickGB?: number,
        casLatency?: number) {

        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.speed = speed;
        this.type = type;
        this.sticks = sticks;
        this.perStickGB = perStickGB;
        this.casLatency = casLatency;


    }
}