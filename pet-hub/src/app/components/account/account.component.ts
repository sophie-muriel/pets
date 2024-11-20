import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  user: any;
  showModal = false;

  fields = [
    { name: 'login', label: 'Login', type: 'text' },
    { name: 'password', label: 'Password', type: 'password' },
    { name: 'name', label: 'Name', type: 'text' },
    { name: 'email', label: 'Email', type: 'email' },
    { name: 'phoneNumber', label: 'Phone', type: 'text' },
    { name: 'role', label: 'Role', type: 'select' },
  ];

  entities = [
    { label: 'Categories', path: 'categories' },
    { label: 'Clients', path: 'clients' },
    { label: 'Pets', path: 'pets' },
    { label: 'Sales', path: 'sales' },
    { label: 'Sale Details', path: 'saleDetails' },
    { label: 'Services', path: 'services' },
    { label: 'Users', path: 'users' },
  ];

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe((user) => {
      this.user = user;
    });
  }

  editUser() {
    this.authService.editUser(this.user).subscribe(
      (response) => {
        if (response.status === 'success') {
          this.showModal = true;
          setTimeout(() => {
            this.showModal = false;
          }, 3000);
        }
      },
      (error) => {
        console.error('Error updating user information:', error);
        alert('Failed to update user information.');
      }
    );
  }

  getIcon(entity: any): string {
    switch (entity.path) {
      case 'categories':
        return 'fas fa-th-large';
      case 'clients':
        return 'fas fa-users';
      case 'pets':
        return 'fas fa-paw';
      case 'sales':
        return 'fas fa-shopping-cart';
      case 'saleDetails':
        return 'fas fa-file-alt';
      case 'services':
        return 'fas fa-cut';
      case 'users':
        return 'fas fa-user';
      default:
        return 'fas fa-question-circle';
    }
  }

  navigateToEntity(entity: any): void {
    this.router.navigate([`/account/${entity.path}`]);
  }
}