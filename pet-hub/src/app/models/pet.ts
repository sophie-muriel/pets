import { Client } from "./client";

export interface Pet {
  id: number;
  name: string;
  owner: Client;
  species: string;
  gender: 'Male' | 'Female';
  age: number;
  weight: number;
  medicalHistoryLink?: string;
}