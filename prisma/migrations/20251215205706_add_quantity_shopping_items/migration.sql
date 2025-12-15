/*
  Warnings:

  - Added the required column `quantity` to the `shopping_items` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "shopping_items" ADD COLUMN     "quantity" INTEGER NOT NULL;
