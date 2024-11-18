import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { AccountComponent } from './components/account/account.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ServicesComponent } from './components/services/services.component';
import { DashboardComponent } from './components/account/dashboard/dashboard.component';
import { InfoComponent } from './components/account/info/info.component';
import { UsersComponent } from './components/account/dashboard/users/users.component';
import { CategoriesComponent } from './components/account/dashboard/categories/categories.component';
import { ClientsComponent } from './components/account/dashboard/clients/clients.component';
import { PetsComponent } from './components/account/dashboard/pets/pets.component';
import { SalesComponent } from './components/account/dashboard/sales/sales.component';
import { ScrollService } from './services/scroll.service';
import { ContactComponent } from './components/contact/contact.component';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    AccountComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    ServicesComponent,
    DashboardComponent,
    InfoComponent,
    UsersComponent,
    CategoriesComponent,
    ClientsComponent,
    PetsComponent,
    SalesComponent,
    ContactComponent,
  ],
  imports: [BrowserModule, AppRoutingModule,],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}