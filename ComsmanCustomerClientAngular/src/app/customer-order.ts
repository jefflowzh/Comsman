import { LineItem } from './line-item';
import { Customer } from './customer';
import { Coupon } from './coupon';
import { OrderStatusEnum } from './order-status-enum.enum';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { Staff } from './staff';

export class CustomerOrder {

    customerOrderId: number;
    orderDate: Date;
    fulfilledDate: Date;
    totalPrice: number;
    requiresDelivery: boolean;
    deliveryAddress: string;
    billingAddress: string;
    lineItems: LineItem[];
    customer: Customer;
    coupon: Coupon;
    staff: Staff;
    orderStatusEnum: OrderStatusEnum;

    constructor(
        customerOrderId?: number,
        orderDate?: Date,
        fulfilledDate?: Date,
        totalPrice?: number,
        requiresDelivery?: boolean,
        deliveryAddress?: string,
        billingAddress?: string,
        lineItems?: LineItem[],
        customer?: Customer,
        coupon?: Coupon,
        staff?: Staff,
        orderStatusEnum?: OrderStatusEnum
    ){
        this.customerOrderId = customerOrderId;
        this.orderDate = orderDate;
        this.fulfilledDate = fulfilledDate;
        this.totalPrice = totalPrice;
        this.requiresDelivery = requiresDelivery;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.lineItems = lineItems;
        this.customer = customer;
        this.coupon = coupon;
        this.staff = staff;
        this.orderStatusEnum = orderStatusEnum;
    }

    
}
