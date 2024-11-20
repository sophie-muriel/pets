import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  private currentUserSubject = new BehaviorSubject<any>(null);

  constructor(private http: HttpClient, private router: Router) {
    const storedLoginState = localStorage.getItem('isLoggedIn');
    const storedUserData = localStorage.getItem('currentUser');

    if (storedLoginState === 'true' && storedUserData) {
      this.isLoggedInSubject.next(true);
      this.currentUserSubject.next(JSON.parse(storedUserData));
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
            this.currentUserSubject.next(response.data);
            localStorage.setItem('currentUser', JSON.stringify(response.data));
          }
        }),
        catchError((error) => {
          console.error('Login failed:', error);
          return of({
            status: 'error',
            message: 'Login failed, please try again',
          });
        })
      );
  }

  logout() {
    this.isLoggedInSubject.next(false);
    this.currentUserSubject.next(null);
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('currentUser');
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return this.isLoggedInSubject.asObservable();
  }

  getCurrentUser() {
    return this.currentUserSubject.asObservable();
  }

  editUser(updatedUser: any) {
    const userId = updatedUser.id;
    return this.http
      .put<any>(`http://localhost:8081/users/edit/${userId}`, updatedUser)
      .pipe(
        tap((response) => {
          if (response.status === 'success') {
            this.currentUserSubject.next(response.data);
            localStorage.setItem('currentUser', JSON.stringify(response.data));
          }
        }),
        catchError((error) => {
          console.error('Error updating user:', error);
          return of({
            status: 'error',
            message: 'Failed to update user information',
          });
        })
      );
  }
}
