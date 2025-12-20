import { ErrorCode } from '@/domain/enums/error-code.enum'

export const errorStatusMap: Record<ErrorCode, number> = {
//   [ErrorCode.SHOPPING_LIST_NOT_FOUND]: 404,
//   [ErrorCode.SHOPPING_LIST_TITLE_REQUIRED]: 400,

//   [ErrorCode.ITEM_NAME_REQUIRED]: 400,
//   [ErrorCode.ITEM_QUANTITY_INVALID]: 400,
//   [ErrorCode.ITEM_PRICE_INVALID]: 400,

  [ErrorCode.INTERNAL_ERROR]: 500,
  [ErrorCode.VALIDATION_ERROR]: 400
}
