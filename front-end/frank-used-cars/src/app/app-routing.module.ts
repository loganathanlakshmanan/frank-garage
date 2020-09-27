import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OverviewProductItemComponent} from "./components/overview-car/overview-product-item.component";
import {PageNotFoundComponent} from './components/shared/page-not-found/page-not-found.component';

import {ShoppingCartComponent} from './components/shopping-cart/shopping-cart.component';

const routes: Routes = [
  {path: '', redirectTo: '/shop', pathMatch: 'full'},
  {path: 'shop', component: ShoppingCartComponent},
  {path: 'checkout', component: OverviewProductItemComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {

}
