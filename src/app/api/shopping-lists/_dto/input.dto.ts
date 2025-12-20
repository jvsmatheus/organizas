import { ErrorMessages } from "@/domain/enums/error-message.enum";
import z from "zod"

export const createShoppingListSchema = z.object({
  title: z.string(ErrorMessages.TITLE_REQUIRED).min(1),
  marketName: z.string().optional(),
  purchaseDate: z.date().optional(),
});

export type CreateShoppingListInputDto = z.infer<typeof createShoppingListSchema>;