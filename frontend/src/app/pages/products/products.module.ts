import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { RegisterProductComponent } from './register-product/register-product.component';
import { ProductManagementComponent } from './product-management/product-management.component';
import { ComponentsModule } from 'src/app/shared/components.module';


@NgModule({
  declarations: [
    ProductDetailComponent,
    RegisterProductComponent,
    ProductManagementComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    ComponentsModule
  ]
})
export class ProductsModule { }
