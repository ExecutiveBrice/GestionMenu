import { Etape } from "./etape";
import { IngredientUsed } from "./ingredientUsed";
import { RecetteName } from "./recetteName";
import { User } from "./user";


export class Recette {
  id: number;
  recetteName: RecetteName;
  user: User;
  etapes: Etape[];
  ingredientsUsed: IngredientUsed[];
  prepa:number;
  cuisson:number;
  pour:number;
  pourType:number;
  image: string | ArrayBuffer
  stars: User[]

  constructor(){
    this.stars = []
    this.etapes = []
    this.user = new User()
    this.recetteName = new RecetteName;
    this.ingredientsUsed = []
    this.prepa = 5
    this.cuisson = 10
    this.pour = 4
    this.pourType = 1
  }
}