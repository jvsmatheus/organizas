import { mapErrorToHttp } from "../_errors/map-error-http-status";
import { CreateShoppingListInputDto } from "./_dto/input.dto";

// GET / POST -> api/shopping-lists
export async function POST(request: Request) {
    try {
        // 1. Parse do body
        const body = await request.json() as CreateShoppingListInputDto;

        // 2. Chamar o service (ainda não existe)

        // 3. Response de sucesso
        return Response.json(
            {
                success: true,
                data: {}
            },
            { status: 201 }
        );
    } catch (error) {
        const httpError = mapErrorToHttp(error);
        return Response.json(httpError.body, { status: httpError.status });
    }
}