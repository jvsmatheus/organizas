import { ErrorCode } from "@/domain/errors/enums/error-code.enum"

export class DomainError extends Error {
  readonly code: ErrorCode

  constructor(code: ErrorCode, message: string) {
    super(message)
    this.code = code
    this.name = 'DomainError'
  }
}
