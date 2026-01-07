export type ResponseBaseSuccess<T> = {
  success: true
  data: T
}

export type ResponseBaseError = {
  success: false
  error: {
    code: string
    message: string
  }
}

export type ApiResponse<T> = ResponseBaseSuccess<T> | ResponseBaseError