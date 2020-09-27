import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {finalize} from "rxjs/operators";
import {SpinnerService} from "../services/spinner.service";

@Injectable({
  providedIn: 'root'
})
export class HttpTokenInterceptor implements HttpInterceptor {

  constructor(private spinnerService: SpinnerService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const request = req.clone({setHeaders: {'user-token': 'abcd'}});
    this.spinnerService.show();
    return next.handle(request).pipe(
      finalize(() => this.spinnerService.hide())
    );
  }
}
