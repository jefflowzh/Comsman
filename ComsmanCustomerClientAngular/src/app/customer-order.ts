import { LineItem } from './line-item';
import { Customer } from './customer';
import { Coupon } from './coupon';
import { OrderStatusEnum } from './order-status-enum.enum';

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
    orderStatusEnum: OrderStatusEnum;
}
