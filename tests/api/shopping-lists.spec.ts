import { POST } from "@/app/api/shopping-lists/route";
import { makePostRequest } from "../tests.helper";
import { ErrorCode } from "@/domain/enums/error-code.enum";
import { ErrorMessages } from "@/domain/enums/error-message.enum";

describe("POST /Shopping-lists", () => {
    // body vazio
    test("return 400 when body is invalid", async () => {
        const request = makePostRequest({});

        const response = await POST(request);
        const json = await response.json();

        expect(response.status).toBe(400);
        expect(json.success).toBe(false);
        expect(json.error.code).toBe(ErrorCode.VALIDATION_ERROR);
        expect(json.error.message).toBe(ErrorMessages.TITLE_REQUIRED);
    });

    // title ausente

    // title não é uma string

    // purchaseDate é uma data

    // body com title correto
});