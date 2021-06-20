
import { Recette } from "./recette";
import { RecetteType } from "./recetteType";



export class RecetteName {

  nom: string;
  type: RecetteType;
  recettes: Recette[];



  constructor() {
    this.recettes = []
  }
}