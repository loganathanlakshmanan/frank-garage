import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from "@angular/core";
import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {ActivatedRoute} from "@angular/router";
import {of} from "rxjs";
import {Product} from "../../models/product";
import {Warehouse} from "../../models/warehouse";

import {DutchCurrencyPipe} from "../../pipes/dutch-currency.pipe";

import {OverviewProductItemComponent} from './overview-product-item.component';

describe('OverviewProductItemComponent', () => {
  let component: OverviewProductItemComponent;
  let fixture: ComponentFixture<OverviewProductItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OverviewProductItemComponent, DutchCurrencyPipe],
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            queryParams:
              of({
                id: 1,
                warehouseId: 1
              })
          }
        }
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    })
           .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OverviewProductItemComponent);
    component = fixture.componentInstance;
    component.productItem = new Product(1, 'Mercedes', 'Benz', 2005, 293.3, true, '19-10-2005', '1');
    component.warehouseItem = new Warehouse(1, 'Warehouse A', 2323, 2323);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
