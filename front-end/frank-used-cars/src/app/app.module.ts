import {AgmCoreModule} from "@agm/core";
import {registerLocaleData} from "@angular/common";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {OverviewProductItemComponent} from './components/overview-car/overview-product-item.component';
import {FooterComponent} from './components/shared/footer/footer.component';
import {HeaderComponent} from './components/shared/header/header.component';
import {NavComponent} from './components/shared/nav/nav.component';
import {PageNotFoundComponent} from './components/shared/page-not-found/page-not-found.component';
import {SpinnerComponent} from './components/shared/spinner/spinner.component';
import {FiltersComponent} from './components/shopping-cart/filters/filters.component';
import {ProductItemComponent} from './components/shopping-cart/product-list/product-item/product-item.component';
import {ProductListComponent} from './components/shopping-cart/product-list/product-list.component';
import {ShoppingCartComponent} from './components/shopping-cart/shopping-cart.component';
import {HttpTokenInterceptor} from "./interceptors/http.token.interceptor";
import {DutchCurrencyPipe} from './pipes/dutch-currency.pipe';

registerLocaleData(localeDe, 'nl-NL', localeDeExtra);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    ShoppingCartComponent,
    ProductListComponent,
    ProductItemComponent,
    PageNotFoundComponent,
    DutchCurrencyPipe,
    FiltersComponent,
    OverviewProductItemComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'KEY_TO_BE_ADDED'
    })
  ],
  providers: [HttpClient, {provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true}],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule {
}
