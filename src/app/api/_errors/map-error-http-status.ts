import { DomainError } from '@/domain/errors/domain-error.model'
import { errorStatusMap } from './error-status-map'

export function mapErrorToHttp(error: unknown) {
    if (error instanceof DomainError) {
        const status = errorStatusMap[error.code] ?? 400

        return {
            status,
            body: {
                success: false,
                error: {
                    code: error.code,
                    message: error.message
                }
            }
        }
    }

    return {
        status: 500,
        body: {
            success: false,
            error: {
                code: 'INTERNAL_ERROR',
                message: 'Erro inesperado'
            }
        }
    }
}
