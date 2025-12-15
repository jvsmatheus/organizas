export interface ShoppingItemInputDto {
  id?: number;
  name: string;
  quantity: number;
  value: number;
  isChecked: boolean;
  createdAt?: Date;
  updatedAt?: Date;
};