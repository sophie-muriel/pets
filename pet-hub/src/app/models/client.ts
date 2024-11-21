export interface Client {
  id: number | null;
  name: string;
  email: string;
  phoneNumber?: string;
  address: string;
}