import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
    const storedLoginState = localStorage.getItem('isLoggedIn');
    if (storedLoginState === 'true') {
      this.isLoggedInSubject.next(true);
    }
  }

  login(credentials: { login: string; password: string }) {
    return this.http
      .post<any>('http://localhost:8081/users/login', credentials)
      .pipe(
        tap((response) => {
          if (response.status === 'success') {
            this.isLoggedInSubject.next(true);
            localStorage.setItem('isLoggedIn', 'true');
          }
        })
      );
  }

  logout() {
    this.isLoggedInSubject.next(false);
    localStorage.removeItem('isLoggedIn');
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return this.isLoggedInSubject.asObservable();
  }
}
