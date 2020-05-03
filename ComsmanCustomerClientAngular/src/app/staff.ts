// public Staff(StaffAccessRightEnum role, String firstName, String lastName, String address, String email, String password, String contactNumber) {
//     super(firstName, lastName, address, email, password, contactNumber);
//     this.role = role;
//     assignedComputerSets = new ArrayList<>();
//     deliveries = new ArrayList<>();
// }

import { LineItem } from './line-item';
import { CustomerOrder } from './customer-order';
import { ComputerPart } from './computer-part'
import { ComputerSet } from './computer-set';
import { OrderStatusEnum } from './order-status-enum.enum';

export class Staff {
    userId: number;
    firstName: string;
    lastName: string;
    address: string;
    email: string;
    password: string;
    contactNumber: string;
    role: OrderStatusEnum;
    assignedComputerSets: ComputerSet[];
    deliveries: CustomerOrder[];

    constructor(userId?: number, firstName?: string, lastName?: string, address?: string, email?: string, password?: string,
        contactNumber?: string, role?: OrderStatusEnum, assignedComputerSets?: ComputerSet[], deliveries?: CustomerOrder[]) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.role = role;
        this.assignedComputerSets = assignedComputerSets;
        this.deliveries = deliveries;
    }
}