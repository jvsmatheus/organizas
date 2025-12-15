-- CreateTable
CREATE TABLE "shopping_items" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "value" INTEGER,
    "is_checked" BOOLEAN NOT NULL DEFAULT false,
    "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3) NOT NULL,

    CONSTRAINT "shopping_items_pkey" PRIMARY KEY ("id")
);
