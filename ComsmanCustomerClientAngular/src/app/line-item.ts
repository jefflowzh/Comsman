import { Product } from './product';
import { ComputerSet } from './computer-set';

export class LineItem {

    lineItemId: number;
    product: Product;
    quantity: number;
    computerSet: ComputerSet;

    constructor(lineItemId?: number, product?: Product, quantity?: number, computerSet?: ComputerSet) {
        this.lineItemId = lineItemId;
        this.product = product;
        this.quantity = quantity;
        this.computerSet = computerSet;
    }
}