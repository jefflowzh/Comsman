import { ComputerPart } from './computer-part'

export class HDD extends ComputerPart {

    manufacturer: string;
    type: string;
    capacity: number;
    formFactor: string;
    interfaceForm: string;

    // constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean,
    //     manufacturer?: string,
    //     type?: string,
    //     capacity?: number,
    //     formFactor?: string,
    //     Interface?: string) {

    //     super(productId, name, price, inventoryQuantity, image, isDisabled);
    //     this.manufacturer = manufacturer;
    //     this.type = type;
    //     this.capacity = capacity;
    //     this.formFactor = formFactor;
    //     this.interfaceForm = Interface;
    // }


}
