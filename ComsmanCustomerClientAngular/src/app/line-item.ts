import { Product } from './product';
import { ComputerSet } from './computer-set';

export class LineItem {

    lineItemId: number;
    product: Product;
    computerSet: ComputerSet;
    quantity: number;

    constructor(lineItemId?: number, product?: Product, computerSet?: ComputerSet, quantity?: number) {
        this.lineItemId = lineItemId;
        this.product = product;
        this.computerSet = computerSet;
        this.quantity = quantity;
    }
}