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
  showModal = false;  // To control the modal visibility
  modalTitle = '';
  modalMessage = '';
  confirmButtonText = 'OK';  // Default confirm button text

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onLogin(): void {
    const credentials = { login: this.login, password: this.password };
  
    this.authService.login(credentials).subscribe(
      (response) => {
        this.router.navigate(['/account']);
      },
      (error) => {
        this.showModalMessage('Login Failed', error.message || 'Invalid username or password');
      }
    );
  }
  

  // Method to show the modal with an error message
  showModalMessage(title: string, message: string): void {
    this.modalTitle = title;
    this.modalMessage = message;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }
}