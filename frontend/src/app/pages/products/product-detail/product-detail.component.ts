import { Component, Input } from '@angular/core';
import { IProduct } from 'src/app/shared/types/product.ypes';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent {

  @Input() product: IProduct = {
    "id": 3,
    "name": "Macbook Pro",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "price": 1250.0,
    "imgUrl": "https://rw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg",
    "date": "2020-07-14T10:00:00Z",
    "categories": [
      {
        "id": 3,
        "name": "Computadores"
      }
    ]
  }

  getProductImageOrPlaceholder(product: IProduct): string {
    if (!this.product.imgUrl) {
      return '../../../../assets/images/placeholder.webp'
    }
    return this.product.imgUrl;
  }

}
