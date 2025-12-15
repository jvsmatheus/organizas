/*
  Warnings:

  - You are about to alter the column `name` on the `shopping_items` table. The data in that column could be lost. The data in that column will be cast from `Text` to `VarChar(255)`.
  - Made the column `value` on table `shopping_items` required. This step will fail if there are existing NULL values in that column.

*/
-- AlterTable
ALTER TABLE "shopping_items" ALTER COLUMN "name" SET DATA TYPE VARCHAR(255),
ALTER COLUMN "value" SET NOT NULL,
ALTER COLUMN "updated_at" DROP NOT NULL;
