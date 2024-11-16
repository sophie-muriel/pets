import { Client } from "./client";
import { User } from "./user";

export interface Sale {
  id: number;
  user: User;
  client: Client;
  totalPrice?: number;
  saleDate?: Date;
  status: 'Completed' | 'Pending' | 'Cancelled';
  paymentMethod?: 'Cash' | 'Card' | 'Transfer';
}