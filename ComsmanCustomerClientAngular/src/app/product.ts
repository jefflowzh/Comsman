export class Product {

    productId: number;
    name: string;
    price: number;
    inventoryQuantity: number;
    image: string;
    isDisabled: boolean

    constructor(productId?: number, name?: string, price?: number, inventoryQuantity?: number, image?: string, isDisabled?: boolean) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.image = image;
        this.isDisabled = isDisabled;
    }
}
