import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterLink } from '@angular/router';
import { CardComponent } from './components/card/card.component';
import { ArrowButtonComponent } from './components/arrow-button/arrow-button.component';
import { MatIconModule } from '@angular/material/icon';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { CategoryBarComponent } from './components/category-bar/category-bar.component';


@NgModule({
  declarations: [
    NavbarComponent,
    CardComponent,
    ArrowButtonComponent,
    ProductCardComponent,
    SearchBarComponent,
    CategoryBarComponent
  ],
  imports: [
    CommonModule,
    RouterLink,
    MatIconModule
  ],
  exports: [
    NavbarComponent,
    CardComponent,
    ArrowButtonComponent,
    ProductCardComponent,
    SearchBarComponent,
    CategoryBarComponent
  ]
})
export class ComponentsModule { }
