import { ICategory } from "./category.types";

export interface IProduct {
  id: number;
  name: string;
  description: string;
  price: number;
  imgUrl: string;
  date: string;
  categories: ICategory[]
}
