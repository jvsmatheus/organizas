import handleErrors from "../_errors/handle-errors";
import { mapDomainErrorToHttp } from "../_errors/map-domain-error-http-status";
import { createShoppingListSchema } from "./_dto/input.dto";


// GET / POST -> api/shopping-lists
export async function POST(request: Request) {
    try {
        // 1. Parse do body
        const body = createShoppingListSchema.parse(await request.json());

        // 2. Chamar o service (ainda não existe)

        // 3. Response de sucesso
        return Response.json(
            {
                success: true,
                data: {}
            },
            { status: 201 }
        );
    } catch (error: unknown) {
        const httpError = handleErrors(error);
        console.log(httpError)
        return Response.json(httpError.body, { status: httpError.status });
    }
}