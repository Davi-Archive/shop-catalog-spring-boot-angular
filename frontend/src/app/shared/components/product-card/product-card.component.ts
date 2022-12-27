import { Component, Input, OnInit } from '@angular/core';
import { IProduct } from '../../types/product.ypes';
import { PlaceholderImageService } from '../../utils/placeholder-image.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {
  public imageUrl: string = ''
  @Input() product?: IProduct;

  constructor(private placeholder: PlaceholderImageService) { }

  ngOnInit(): void {
    this.imageUrl = this.placeholder.getProductImageOrPlaceholder(this.product!);
  }




}
