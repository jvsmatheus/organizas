import { prisma } from "../prisma";
import { PrismaShoppingListRepository } from "../prisma-repositories/shopping-list.prisma-repository";

export const shoppingListRepository = new PrismaShoppingListRepository(prisma);