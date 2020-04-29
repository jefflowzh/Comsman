import { Product } from './product';

export class ComputerPart extends Product {

    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: Boolean) {
        super(productId, name, price, inventoryQuantity, image, isDisabled);
    }
}
