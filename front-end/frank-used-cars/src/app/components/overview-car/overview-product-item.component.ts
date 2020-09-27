import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {forkJoin, Observable, Subscription} from "rxjs";
import {Product} from "../../models/product";
import {Warehouse} from "../../models/warehouse";
import {UsedCarService} from "../../services/used-car.service";
import {WarehouseService} from "../../services/warehouse.service";

@Component({
  selector: 'app-checkout-cart',
  templateUrl: './overview-product-item.component.html',
  styleUrls: ['./overview-product-item.component.css']
})
export class OverviewProductItemComponent implements OnInit {

  productId: string = '';
  warehouseId: string = '';
  state$: Observable<object>;
  productItem: Product;
  warehouseItem: Warehouse;
  serviceCallSubscription$: Subscription = new Subscription();
  private IsLoaded: boolean;

  constructor(
    private _route: ActivatedRoute, private usedCarService: UsedCarService, private warehouseService: WarehouseService
  )
  {
  }

  ngOnInit() {
    this._route.queryParams.subscribe(data => {
      this.productId = data.id;
      this.warehouseId = data.warehouseId;
    });

    let productObservable = this.usedCarService.getProductByid(this.productId);
    let warehouseObservable = this.warehouseService.getWarehouseById(this.warehouseId);

    this.serviceCallSubscription$ = forkJoin([warehouseObservable, productObservable]).subscribe(result => {
      this.warehouseItem = result[0];
      this.productItem = result[1];
      this.IsLoaded = true;
    });

  }

}
