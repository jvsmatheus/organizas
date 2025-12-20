import { CreateShoppingListInputDto } from './api/shopping-lists/_dto/input.dto';
export default function Home() {
  const handleAddShoppingItem = async (item: CreateShoppingListInputDto) => {
    if (!item) return;
    
  };

  return (
    <div className="flex align-center justify-center h-full">
      <h1 className="w-fit">Welcome to Organizas!</h1>
    </div>
  );
}
