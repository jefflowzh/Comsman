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

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService, private router: Router) { }

  ngOnInit() {
    this.productType = "Search Result"

    this.router.events.subscribe((event: any) => {
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

}
