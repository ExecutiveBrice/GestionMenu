import { Ingredient } from "./ingredient";
import { Recette } from "./recette";

export class IngredientUsed {
  id:number;
  ingredient: Ingredient;
  quantite: number;
  checked:boolean;


}