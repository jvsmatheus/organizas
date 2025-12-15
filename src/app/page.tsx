import { ShoppingItemInputDto } from "@/models/dto/shopping-item/shopping-item";

export default function Home() {
  const handleAddShoppingItem = async (item: ShoppingItemInputDto) => {
    if (!item) return;
    
  };

  return (
    <div className="flex align-center justify-center h-full">
      <h1 className="w-fit">Welcome to Organizas!</h1>
    </div>
  );
}
