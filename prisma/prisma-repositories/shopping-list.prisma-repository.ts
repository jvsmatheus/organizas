import { ShoppingListRepository } from "@/domain/shopping-lists/repositories/shopping-list.repository";
import { PrismaClient } from "@prisma/client";

export class PrismaShoppingListRepository implements ShoppingListRepository {

    constructor(private readonly prisma: PrismaClient) {}

    create(shoppingList: Omit<ShoppingList, "id">): Promise<ShoppingList> {
        console.log('aaaa', shoppingList);
        throw new Error("Method not implemented.");
    }

}