import { IngredientUsed } from "./ingredientUsed";
import { Jour } from "./jour";
import { User } from "./user";


export class Menu {
  id: number;
  user: User;
  numeroSemaine: number;
  semaine: Jour[];
  ingredientsUsed: IngredientUsed[];


  constructor(numSem){
    this.numeroSemaine = numSem
    this.semaine = []
    this.semaine.push(new Jour("Lundi"))
    this.semaine.push(new Jour("Mardi"))
    this.semaine.push(new Jour("Mercredi"))
    this.semaine.push(new Jour("Jeudi"))
    this.semaine.push(new Jour("Vendredi"))
    this.semaine.push(new Jour("Samedi"))
    this.semaine.push(new Jour("Dimanche"))

    this.ingredientsUsed = []
  }



}


