import { ShoppingListItem } from "./shopping-list-item.model"

export interface ShoppingList {
  id: string
  title: string
  marketName?: string
  purchaseDate?: Date
  items: ShoppingListItem[]
}
