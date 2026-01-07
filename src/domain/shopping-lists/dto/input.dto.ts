import { ErrorMessages } from "@/domain/errors/enums/error-message.enum";
import z from "zod";

export const createShoppingListSchema = z.object({
  title: z.string(ErrorMessages.TITLE_REQUIRED).min(4, ErrorMessages.MIN_TITLE_LENGTH_INVALID).max(255, ErrorMessages.MAX_TITLE_LENGTH_INVALID).trim(),
  marketName: z.string(ErrorMessages.INTERNAL_ERROR).optional(),
  purchaseDate: z.coerce.date(ErrorMessages.INTERNAL_ERROR).optional(),
});

export type CreateShoppingListInputDto = z.infer<typeof createShoppingListSchema>;

export type CreateShoppingListOutputDto = {
  id: string;
  title: string;
  marketName?: string;
  purchaseDate?: Date;
}; 