import { DomainError } from '@/domain/errors/models/domain-error.model'
import { errorStatusMap } from './error-status-map'

export function mapDomainErrorToHttp(error: DomainError) {
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
