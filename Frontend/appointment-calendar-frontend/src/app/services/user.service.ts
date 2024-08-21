import { Injectable, inject } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = "https://localhost:8080";
  private urlUser = "users"

  constructor(private http: HttpClient) {}

  getProducts(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/${this.urlUser}`);
  }

  getProductById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${this.urlUser}/${id}`);
  }

}