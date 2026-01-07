import { ErrorCode } from '@/domain/errors/enums/error-code.enum';
import { ZodError } from 'zod';

export function mapValidationErrorToHttp(error: ZodError) {
    const errorJson = JSON.parse(JSON.stringify(error.issues))[0];

    return {
        status: 400,
        body: {
            success: false,
            error: {
                code: ErrorCode.VALIDATION_ERROR,
                message: errorJson.message
            }
        }
    }
}