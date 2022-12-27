import { Injectable } from '@angular/core';
import { IProduct } from '../types/product.ypes';

@Injectable({
  providedIn: 'root'
})

export class PlaceholderImageService {
  constructor() { }

  getProductImageOrPlaceholder(product: IProduct): string {
    if (!product.imgUrl) {
      return '../../../../assets/images/placeholder.webp'
    }
    return product.imgUrl;
  }
}
