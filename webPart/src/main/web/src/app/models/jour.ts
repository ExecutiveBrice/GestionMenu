import { Recette } from "./recette";
import { RecetteUsed } from "./recetteUsed";


export class Jour {
  id: number;
  nomJour: string;
  recettesmidi: RecetteUsed[];
  recettessoir: RecetteUsed[];

  constructor(nom){
    this.nomJour = nom
    this.recettesmidi = []
    this.recettessoir = []
  }
}
