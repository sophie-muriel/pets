import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl = 'http://localhost:8081/users/login';

  constructor(private http: HttpClient) {}

  login(login: string, password: string): Observable<any> {
    const credentials = { login, password };
    return this.http.post<any>(this.apiUrl, credentials);
  }
}
