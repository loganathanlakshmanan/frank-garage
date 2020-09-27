import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {warehousesUrl} from "../config/api";
import {Warehouse} from "../models/warehouse";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  constructor(private http: HttpClient) {
  }

  getWarehouses(): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(warehousesUrl);
  }

  getWarehouseById(id: string): Observable<Warehouse> {
    return this.http.get<Warehouse>(warehousesUrl + `/${id}`);
  }
}
