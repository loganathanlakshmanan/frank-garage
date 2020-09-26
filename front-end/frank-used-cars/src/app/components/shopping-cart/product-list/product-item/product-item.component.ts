import {Component, Input, OnInit} from '@angular/core';
import {Product} from 'src/app/models/product';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {

  @Input() productItem: Product;

  @Input() addedToWishlist: boolean;

  constructor() {
  }

  ngOnInit() {
  }

}
