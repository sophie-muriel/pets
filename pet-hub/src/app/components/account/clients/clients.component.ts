import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client';

@Component({
  selector: 'app-client',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css'],
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  clientFormData: Client = {
    id: null,
    name: '',
    email: '',
    phoneNumber: '',
    address: '',
  };
  isEditing = false;

  showModal = false;
  modalTitle = '';
  modalMessage = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  showModalMessage(title: string, message: string): void {
    this.modalTitle = title;
    this.modalMessage = message;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  loadClients(): void {
    this.clientService.getClients().subscribe({
      next: (response: any) => {
        // Handle both array and object responses
        this.clients = Array.isArray(response) ? response : response.data || [];
      },
      error: (error) => {
        console.error('Error loading clients:', error);
        this.showModalMessage('Error', 'Failed to load client data.');
      }
    });
  }

  saveClient(): void {
    if (this.validateForm()) {
      const clientData = {
        name: this.clientFormData.name,
        email: this.clientFormData.email,
        phoneNumber: this.clientFormData.phoneNumber,
        address: this.clientFormData.address
      };

      this.clientService.saveClient(clientData).subscribe({
        next: () => {
          this.showModalMessage('Success', 'Client saved successfully!');
          this.loadClients();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error saving client:', error);
          this.showModalMessage('Error', 'Failed to save client.');
        }
      });
    }
  }

  editClient(): void {
    if (!this.isEditing) {
      this.isEditing = true;
      return;
    }

    if (this.validateForm() && this.clientFormData.id !== null) {
      this.clientService.editClient(this.clientFormData.id, this.clientFormData).subscribe({
        next: () => {
          this.showModalMessage('Success', 'Client updated successfully!');
          this.loadClients();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error editing client:', error);
          this.showModalMessage('Error', 'Failed to edit client.');
        }
      });
    }
  }

  deleteClient(): void {
    if (this.clientFormData.id !== null) {
      if (confirm('Are you sure you want to delete this client?')) {
        this.clientService.deleteClient(this.clientFormData.id).subscribe({
          next: () => {
            this.showModalMessage('Success', 'Client deleted successfully!');
            this.loadClients();
            this.resetForm();
          },
          error: (error) => {
            console.error('Error deleting client:', error);
            this.showModalMessage('Error', 'Failed to delete client.');
          }
        });
      }
    } else {
      this.showModalMessage('Error', 'Please select a client to delete.');
    }
  }

  selectClient(client: Client): void {
    this.clientFormData = { ...client };
    this.isEditing = true;
  }

  resetForm(): void {
    this.clientFormData = {
      id: null,
      name: '',
      email: '',
      phoneNumber: '',
      address: '',
    };
    this.isEditing = false;
  }

  validateForm(): boolean {
    if (!this.clientFormData.name.trim()) {
      this.showModalMessage('Error', 'Please enter client name.');
      return false;
    }
    if (!this.clientFormData.email.trim()) {
      this.showModalMessage('Error', 'Please enter client email.');
      return false;
    }
    return true;
  }
}