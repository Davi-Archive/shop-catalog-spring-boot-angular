import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterLink } from '@angular/router';
import { CardComponent } from './components/card/card.component';
import { ArrowButtonComponent } from './components/arrow-button/arrow-button.component';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [
    NavbarComponent,
    CardComponent,
    ArrowButtonComponent
  ],
  imports: [
    CommonModule,
    RouterLink,
    MatIconModule
  ],
  exports:[
    NavbarComponent,
    CardComponent,
    ArrowButtonComponent
  ]
})
export class ComponentsModule { }
