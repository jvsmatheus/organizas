import handleErrors from "../../../domain/errors/handle-errors";
import { createShoppingListSchema } from "../../../domain/shopping-lists/dto/input.dto";


// GET / POST -> api/shopping-lists
export async function POST(request: Request) {
    try {
        // 1. Parse do body
        const body = createShoppingListSchema.parse(await request.json());

        // 2. Chamar o service (ainda não existe)
        console.log(body);

        // 3. Response de sucesso
        return Response.json(
            {
                success: true,
                data: undefined
            },
            { status: 201 }
        );
    } catch (error: unknown) {
        const httpError = handleErrors(error);
        return Response.json(httpError.body, { status: httpError.status });
    }
}