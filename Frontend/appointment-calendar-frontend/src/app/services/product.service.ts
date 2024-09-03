import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = "https://localhost:8080";
  private urlProduct = "products"

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    //return this.http.get<Product[]>(`${this.apiUrl}/${this.urlProduct}`);
    return this.http.get<Product[]>(`https://fake-store-api-2no73ornoa-uc.a.run.app/api/products/all`);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${this.urlProduct}/${id}`);
  }
}
