import { ComputerPart } from './computer-part'

export class MotherBoard extends ComputerPart {


    manufacturer: string;
    formFactor: string;
    socket: string;
    chipset: string;
    memorySlot: number;
    colour: string;
    SLICrossFire: boolean;
    PCIEx16: number;
    m2Slot: number;
    wiFi: boolean;
    suportedMemorySpeed: string[];


    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
        manufacturer?: string,
        formFactor?: string,
        socket?: string,
        chipset?: string,
        memorySlot?: number,
        colour?: string,
        SLICrossFire?: boolean,
        PCIEx16?: number,
        m2Slot?: number,
        wiFi?: boolean,
        suportedMemorySpeed?: string[]) {

        super(productId, name, price, inventoryQuantity, image, isDisabled);
        this.manufacturer = manufacturer;
        this.formFactor = formFactor
        this.socket = socket
        this.chipset = chipset
        this.memorySlot = memorySlot
        this.colour = colour
        this.SLICrossFire = SLICrossFire
        this.PCIEx16 = PCIEx16
        this.m2Slot = m2Slot
        this.wiFi = wiFi
        this.suportedMemorySpeed = suportedMemorySpeed
    }

}