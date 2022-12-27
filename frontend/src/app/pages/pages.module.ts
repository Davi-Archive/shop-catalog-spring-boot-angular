import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CatalogComponent } from './catalog/catalog.component';
import { AdminComponent } from './admin/admin.component';
import { ComponentsModule } from '../shared/components.module';
import { RouterLink } from '@angular/router';



@NgModule({
  declarations: [
    HomeComponent,
    LoginComponent,
    CatalogComponent,
    AdminComponent,
  ],
  imports: [
    RouterLink,
    CommonModule,
    ComponentsModule,
  ],
  exports: [
    HomeComponent,
    LoginComponent
  ]
})
export class PagesModule { }
