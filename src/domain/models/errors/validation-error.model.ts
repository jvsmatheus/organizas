import { ErrorCode } from "@/domain/enums/error-code.enum";

export class ValidationError extends Error {
  readonly code: ErrorCode;

  constructor(code: ErrorCode, message: string) {
    super(message)
    this.code = code
    this.name = 'ValidationError'
  }
}