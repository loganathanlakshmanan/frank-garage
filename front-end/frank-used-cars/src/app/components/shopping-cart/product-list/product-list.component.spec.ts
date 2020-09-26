import {HttpClient, HttpHandler} from "@angular/common/http";
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {DutchCurrencyPipe} from "../../../pipes/dutch-currency.pipe";

import {ProductListComponent} from './product-list.component';

describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ProductListComponent, DutchCurrencyPipe],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [HttpClient, HttpHandler]
    })
           .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
