import { DomainError } from "@/domain/models/errors/domain-error.model";
import { mapDomainErrorToHttp } from "./map-domain-error-http-status";
import { ErrorCode } from "@/domain/enums/error-code.enum";
import { ValidationError } from "@/domain/models/errors/validation-error.mode";
import { ErrorMessages } from "@/domain/enums/error-message.enum";
import { ZodError } from "zod";
import { mapValidationErrorToHttp } from "./map-validation-error-http-status";

export default function handleErrors(error: unknown) {
    if (error instanceof DomainError)
        return mapDomainErrorToHttp(error);

    if (error instanceof ZodError)
        return mapValidationErrorToHttp(error);

    return {
        status: 500,
        body: {
            success: false,
            error: {
                code: ErrorCode.INTERNAL_ERROR,
                message: ErrorMessages.INTERNAL_ERROR
            }
        }
    }
}