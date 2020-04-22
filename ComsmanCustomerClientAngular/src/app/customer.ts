import { LineItem } from './line-item';
import { CustomerOrder } from './customer-order';

export class Customer {
    customerId: number;
    firstName: string;
    lastName: string;
    address: string;
    email: string;
    password: string;
    contactNumber: string;
    isDisabled: Boolean;
    cardNumber: string;
    ccv: string;
    cart: LineItem[];
    loyaltyPoints: number;
    orders: CustomerOrder[];

    constructor(customerId?: number, firstName?: string, lastName?: string, address?: string, email?: string, password?: string,
        contactNumber?: string, isDisabled?: Boolean, cardNumber?: string, ccv?: string, loyaltyPoints?: number) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.isDisabled = isDisabled;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.loyaltyPoints = loyaltyPoints;
    }
}
