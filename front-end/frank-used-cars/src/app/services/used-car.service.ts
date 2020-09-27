import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

import {productsUrl} from 'src/app/config/api';

import {Product} from 'src/app/models/product';

@Injectable({
  providedIn: 'root'
})
export class UsedCarService {

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(productsUrl);
  }

  getProductByid(id: string): Observable<Product> {
    return this.http.get<Product>(productsUrl + `/${id}`);
  }
}
