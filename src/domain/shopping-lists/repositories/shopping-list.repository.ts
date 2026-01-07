export interface ShoppingListRepository {
    create(shoppingList: Omit<ShoppingList, "id">): Promise<ShoppingList>;
}