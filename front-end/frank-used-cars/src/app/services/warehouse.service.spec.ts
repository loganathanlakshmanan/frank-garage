import {HttpClient, HttpHandler} from "@angular/common/http";
import {TestBed} from '@angular/core/testing';

import {WarehouseService} from './warehouse.service';

describe('WarehouseService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [HttpClient, HttpHandler]
  }));

  it('should be created', () => {
    const service: WarehouseService = TestBed.get(WarehouseService);
    expect(service).toBeTruthy();
  });
});
