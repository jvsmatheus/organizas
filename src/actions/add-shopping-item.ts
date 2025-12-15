"use server";

import { ShoppingItemInputDto } from "@/models/dto/shopping-item/shopping-item";
import { prisma } from "../../prisma/database";
import { ShoppingItem } from "../../generated/prisma/client";

export async function addShoppingItem(shoppingItem: ShoppingItemInputDto): Promise<ShoppingItem | undefined> {
    try {
        if (!shoppingItem)
        return;

    const savedShoppingItem = await prisma.shoppingItem.create({
        data: {
            name: shoppingItem.name,
            quantity: shoppingItem.quantity,
            value: shoppingItem.value,
            isChecked: false,
        },
    });

    if (!savedShoppingItem)
        return;

    return savedShoppingItem;
    }
    catch (error) {
        throw error;
    }
}