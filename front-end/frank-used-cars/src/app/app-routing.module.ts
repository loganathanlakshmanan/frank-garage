import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './components/shared/page-not-found/page-not-found.component';

import {ShoppingCartComponent} from './components/shopping-cart/shopping-cart.component';

const routes: Routes = [
  {path: '', redirectTo: '/shop', pathMatch: 'full'},
  {path: 'shop', component: ShoppingCartComponent},
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
