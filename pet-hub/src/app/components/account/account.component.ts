import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  user: any;

  entities = [
    { label: 'Categories', path: 'categories' },
    { label: 'Clients', path: 'clients' },
    { label: 'Pets', path: 'pets' },
    { label: 'Sales', path: 'sales' },
    { label: 'Sale Details', path: 'saleDetails' },
    { label: 'Services', path: 'services' },
    { label: 'Users', path: 'users' },
  ];

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe((user) => {
      this.user = user;
    });
  }
}