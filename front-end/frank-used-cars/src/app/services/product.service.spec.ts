import {HttpClient, HttpHandler} from "@angular/common/http";
import {TestBed} from '@angular/core/testing';

import {ProductService} from './product.service';

describe('ProductService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [HttpClient, HttpHandler]
  }));

  it('should be created', () => {
    const service: ProductService = TestBed.get(ProductService);
    expect(service).toBeTruthy();
  });
});

