import { ErrorCode } from '@/domain/errors/enums/error-code.enum'

export const errorStatusMap: Record<ErrorCode, number> = {
  [ErrorCode.INTERNAL_ERROR]: 500,
  [ErrorCode.VALIDATION_ERROR]: 400
}
