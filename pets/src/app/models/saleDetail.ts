import { Pet } from "./pet";
import { Sale } from "./sale";
import { Service } from "./service";

export interface SaleDetail {
  id: number;
  sale: Sale;
  service: Service;
  pet: Pet;
  scheduledDate: Date;
}