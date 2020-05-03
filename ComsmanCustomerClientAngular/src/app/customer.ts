import { LineItem } from './line-item';
import { CustomerOrder } from './customer-order';
import { ComputerPart } from './computer-part'

export class Customer {
    userId: number;
    firstName: string;
    lastName: string;
    address: string;
    email: string;
    password: string;
    contactNumber: string;
    isDisabled: boolean;
    cardNumber: string;
    ccv: string;
    cart: LineItem[];
    loyaltyPoints: number;
    orders: CustomerOrder[];
    currComputerBuild: ComputerPart[]

    constructor(userId?: number, firstName?: string, lastName?: string, address?: string, email?: string, password?: string,
        contactNumber?: string, isDisabled?: boolean, cardNumber?: string, ccv?: string, loyaltyPoints?: number) {
        this.userId = userId;
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
