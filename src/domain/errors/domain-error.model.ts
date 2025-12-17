import { ErrorCode } from './error-code.model'

export class DomainError extends Error {
  readonly code: ErrorCode

  constructor(code: ErrorCode, message: string) {
    super(message)
    this.code = code
    this.name = 'DomainError'
  }
}
