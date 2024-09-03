import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  providers: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  menuOption: string = '';
  
  productList: Product[] = [];
  images: string[] = [];
  _productService = inject(ProductService); 
  
  constructor(private router: Router) { }
  
  ngOnInit(): void {
    this.loadProducts();
  }
  
  onOption(arg0: string) {
    this.menuOption = arg0;
  }
  
  loadProducts(): void {
    this._productService.getProducts().subscribe((data: Product[]) =>{
      this.productList=data;
    } );
  }
  
  navegate(id: number): void {
    this.router.navigate(['/product', id]);
  }
  navegateCreate() {
    this.router.navigate(['/product'])
  }
}
