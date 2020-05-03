import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionService } from '../session.service';
import { CustomerOrder } from '../customer-order';
import { CustomerService } from '../customer.service';
import { CustomerOrderService } from '../customer-order.service';
import { LineItem } from '../line-item';
import { Customer } from '../Customer';

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {

  lines: LineItem[];
  customer: Customer;
  customerOrders: CustomerOrder[];
  orderId: string;
  current: CustomerOrder;
  // orderId: number;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.orderId = this.activatedRoute.snapshot.paramMap.get('customerOrderId');
    this.customer = this.sessionService.getCurrentCustomer();

    this.customerService.customerOrders(this.customer.email).subscribe(
      response => {
        this.customerOrders = response.orders;
        console.log(this.customerOrders);
        //this.sessionService.setCurrentOrder(this.customerOrders);
        //this.customerOrders.push(this.co);
        for(let order of this.customerOrders){
          if(this.orderId == order.customerOrderId.toString()){
            console.log(order.totalPrice);
            this.current = order;
            this.lines = order.lineItems;
            console.log(this.lines);
          }
        }
      }, error => {
        // this.router.navigate(["/customerLogin"]);
        console.log('********** CustomerLoginComponent.ts customerOrders(): ' + error);
        
      }
    );

  }



}
