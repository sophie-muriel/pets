export interface User {
  id: number;
  login: string;
  password: string;
  name: string;
  email: string;
  phoneNumber?: string;
  role: 'Employee' | 'Admin' | 'Stylist';
}