import { Injectable, inject } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = "https://localhost:8080";
  private urlAuth = "auth"

  constructor(private http: HttpClient) {}

  logIn(userData: {email: string; password: string}) {
    return this.http.post(`${this.apiUrl}/${this.urlAuth}/login`, userData)
      .subscribe({
        next: (response) => {
          console.log('Log in successful:', response);
        },
        error: (error) => {
          console.error('Log in failed:', error);
        }
      });
  }

  signUp(userData: { username: string; email: string; password: string }) {
    return this.http.post(`${this.apiUrl}/${this.urlAuth}/signup`, userData)
      .subscribe({
        next: (response) => {
          console.log('Sign up successful:', response);
        },
        error: (error) => {
          console.error('Sign up failed:', error);
        }
      });
  }

}