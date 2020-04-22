import { Product } from './product';

export class LineItem {

    lineItemId: number;
    product: Product;
    // computerSet: ComputerSet;
    quantity: number;

    constructor(lineItemId?: number, quantity?: number) {
        this.lineItemId = lineItemId;
        this.quantity = quantity;
    }
}