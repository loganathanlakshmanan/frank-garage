import {HttpClient, HttpHandler} from "@angular/common/http";
import {TestBed} from '@angular/core/testing';

import {UsedCarService} from './used-car.service';

describe('UsedCarService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [HttpClient, HttpHandler]
  }));

  it('should be created', () => {
    const service: UsedCarService = TestBed.get(UsedCarService);
    expect(service).toBeTruthy();
  });
});

