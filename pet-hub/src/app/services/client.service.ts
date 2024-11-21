import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Client } from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private baseUrl = 'http://localhost:8081/clients';

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<any>(`${this.baseUrl}/all`).pipe(
      map(response => Array.isArray(response) ? response : response.data || []),
      catchError((error) => {
        console.error('Error fetching clients:', error);
        return of([]);
      })
    );
  }

  getClientById(clientId: number): Observable<Client | null> {
    return this.http.get<any>(`${this.baseUrl}/${clientId}`).pipe(
      map(response => response.data || response),
      catchError((error) => {
        console.error('Error fetching client by ID:', error);
        return of(null);
      })
    );
  }

  saveClient(clientData: Omit<Client, 'id'>): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/save`, clientData).pipe(
      catchError((error) => {
        console.error('Error creating client:', error);
        throw error;
      })
    );
  }

  editClient(clientId: number, clientData: Client): Observable<any> {
    console.log('Editing client data:', clientData); // Add this line for debugging
    return this.http.put<any>(`${this.baseUrl}/edit/${clientId}`, clientData).pipe(
      catchError((error) => {
        console.error('Error updating client:', error);
        throw error;
      })
    );
  }
  

  deleteClient(clientId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/${clientId}`).pipe(
      catchError((error) => {
        console.error('Error deleting client:', error);
        throw error;
      })
    );
  }
}