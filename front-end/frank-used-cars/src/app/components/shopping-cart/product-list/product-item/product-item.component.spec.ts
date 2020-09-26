import {registerLocaleData} from "@angular/common";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {DutchCurrencyPipe} from "src/app/pipes/dutch-currency.pipe";
import {Product} from "../../../../models/product";

import {ProductItemComponent} from './product-item.component';

registerLocaleData(localeDe, 'nl-NL', localeDeExtra);
describe('ProductItemComponent', () => {
  let component: ProductItemComponent;
  let fixture: ComponentFixture<ProductItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ProductItemComponent, DutchCurrencyPipe]
    })
           .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductItemComponent);
    component = fixture.componentInstance;
    component.productItem = new Product(1, 'VW', 'Jetta', 1995, 2323.00, true, '10-10-2004', '1');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
