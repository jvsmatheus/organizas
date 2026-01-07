import { POST } from "@/app/api/shopping-lists/route";
import { ErrorCode } from "@/domain/errors/enums/error-code.enum";
import { ErrorMessages } from "@/domain/errors/enums/error-message.enum";
import { makePostRequest } from "../tests.helper";

describe("POST /Shopping-lists | Validation", () => {
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
    test("return 400 when title is missing", async () => {
        const request = makePostRequest({
            marketName: "Supermarket",
            purchaseDate: "2024-06-01T00:00:00.000Z",
        });

        const response = await POST(request);
        const json = await response.json();

        expect(response.status).toBe(400);
        expect(json.success).toBe(false);
        expect(json.error.code).toBe(ErrorCode.VALIDATION_ERROR);
        expect(json.error.message).toBe(ErrorMessages.TITLE_REQUIRED);
    });

    // title não é uma string
    test("return 400 when title is not string", async () => {
        const request = makePostRequest({
            title: 1
        });

        const response = await POST(request);
        const json = await response.json();

        expect(response.status).toBe(400);
        expect(json.success).toBe(false);
        expect(json.error.code).toBe(ErrorCode.VALIDATION_ERROR);
        expect(json.error.message).toBe(ErrorMessages.TITLE_REQUIRED);
    });

    // purchaseDate é uma data
    test("return 400 when purchaseDate is not date", async () => {
        const request = makePostRequest({
            title: "title",
            marketName: "Supermarket",
            purchaseDate: "banana",
        });

        const response = await POST(request);
        const json = await response.json();

        expect(response.status).toBe(400);
        expect(json.success).toBe(false);
        expect(json.error.code).toBe(ErrorCode.VALIDATION_ERROR);
        expect(json.error.message).toBe(ErrorMessages.INTERNAL_ERROR);
    });

    // body com title correto
    test("return 201 when body is valid", async () => {
        const request = makePostRequest({
            title: "My Shopping List",
            marketName: "Supermarket",
            purchaseDate: "2024-06-01T00:00:00.000Z",
        });

        const response = await POST(request);
        const json = await response.json();

        expect(response.status).toBe(201);
        expect(json.success).toBe(true);
        expect(json.data).toBeDefined();
    });
});