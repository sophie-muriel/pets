import { Category } from "./category";

export interface Service {
  id: number;
  name: string;
  description?: string;
  price: number;
  category: Category;
}