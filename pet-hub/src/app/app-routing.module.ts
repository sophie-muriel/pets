import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ServicesComponent } from './components/services/services.component';
import { LoginComponent } from './components/login/login.component';
import { AccountComponent } from './components/account/account.component';
import { DashboardComponent } from './components/account/dashboard/dashboard.component';
import { InfoComponent } from './components/account/info/info.component';
import { UsersComponent } from './components/account/dashboard/users/users.component';
import { CategoriesComponent } from './components/account/dashboard/categories/categories.component';
import { ClientsComponent } from './components/account/dashboard/clients/clients.component';
import { PetsComponent } from './components/account/dashboard/pets/pets.component';
import { SalesComponent } from './components/account/dashboard/sales/sales.component';
import { ContactComponent } from './components/contact/contact.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'account',
    component: AccountComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'info', component: InfoComponent },
      { path: 'users', component: UsersComponent },
      { path: 'categories', component: CategoriesComponent },
      { path: 'clients', component: ClientsComponent },
      { path: 'pets', component: PetsComponent },
      { path: 'sales', component: SalesComponent },
    ],
  },
  { path: 'contact', component: ContactComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
