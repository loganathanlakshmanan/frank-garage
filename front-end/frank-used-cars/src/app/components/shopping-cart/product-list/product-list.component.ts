import {Component, OnInit} from '@angular/core';
import {Product} from 'src/app/models/product';

import {UsedCarService} from 'src/app/services/used-car.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  productList: Product[] = [];
  wishlist: number[] = [];

  constructor(
    private usedCarService: UsedCarService
  )
  {
  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.usedCarService.getProducts().subscribe((products) => {
      this.productList = products;
      this.sortData();
    });
  }

  sortData() {
    this.productList.sort((a, b) => {
      return <any>new Date(b.date_added) - <any>new Date(a.date_added);
    });
  }
}
