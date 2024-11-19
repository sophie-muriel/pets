import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onLogin(): void {
    const credentials = { login: this.login, password: this.password };

    // Use AuthService's login method
    this.authService.login(credentials).subscribe(
      (response) => {
        console.log('Login successful', response);
        this.router.navigate(['/account']);
      },
      (error) => {
        console.error('Login failed', error);
        this.errorMessage = 'Invalid username or password';
      }
    );
  }
}
