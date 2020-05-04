import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { ProductService } from '../product.service';
import { Product } from '../product';

@Component({
  selector: 'app-view-all-search-results',
  templateUrl: './view-all-search-results.component.html',
  styleUrls: ['./view-all-search-results.component.css']
})
export class ViewAllSearchResultsComponent implements OnInit {

  productType: string;
  allSearchResults: Product[];

  navigationSubscription;

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService, private router: Router) { }

  ngOnInit() {
    this.productType = "Search Result"

    // Get results when the page is first entered
    this.retrieveProductsBySearchTerm();

    // Get results when they search again on this page.
    // Subscribe to the router events - storing the subscription so we can unsubscribe later.
    this.navigationSubscription = this.router.events.subscribe((event: any) => {
      if (event instanceof NavigationEnd) {
        this.retrieveProductsBySearchTerm();
      }
    });
  }

  retrieveProductsBySearchTerm() {
    let searchTerm = this.activatedRoute.snapshot.paramMap.get('searchTerm');
    this.productService.retrieveProductsBySearchTerm(searchTerm).subscribe(
      response => {
        this.allSearchResults = response.products;
        console.log('********** ViewAllSearchResultsComponent.ts retrieve successful!');
      },
      error => {
        console.log('********** HeaderComponent.ts retrieve error:' + error);
      }
    );
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we don't then we will continue to run our
    // retrieveProductsBySearchTerm() method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

}
