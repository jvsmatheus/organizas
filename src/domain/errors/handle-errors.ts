import { ErrorCode } from "@/domain/errors/enums/error-code.enum";
import { ErrorMessages } from "@/domain/errors/enums/error-message.enum";
import { DomainError } from "@/domain/errors/models/domain-error.model";
import { ZodError } from "zod";
import { mapDomainErrorToHttp } from "./map-domain-error-http-status";
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