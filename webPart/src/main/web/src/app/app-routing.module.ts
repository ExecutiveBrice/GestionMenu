import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MesRecettesComponent, AccueilComponent, UtilisateurComponent, ConfirmComponent, RegisterComponent, LoginComponent, ListeComponent, ErrorComponent, IngredientComponent, MenuComponent, RecetteComponent, RecettesComponent} from "./pages";
import { AdminGuard } from  './admin.guard';
import { LoginGuard } from  './login.guard';

const appRoutes: Routes = [
// { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'utilisateurs', component: UtilisateurComponent, canActivate: [AdminGuard]},
  { path: 'menu/:week', component: MenuComponent, canActivate: [LoginGuard]},
  { path: 'menu', component: MenuComponent, canActivate: [LoginGuard]},
  { path: 'liste/:week', component: ListeComponent, canActivate: [LoginGuard]},
  { path: 'liste', component: ListeComponent, canActivate: [LoginGuard]},
  { path: 'recette/:id', component: RecetteComponent},
  { path: 'recette/:id/:qt', component: RecetteComponent},
  { path: 'mesrecettes', component: MesRecettesComponent, canActivate: [LoginGuard]},
  { path: 'recettes/:type', component: RecettesComponent},
  { path: 'accueil', component: AccueilComponent},
  { path: 'ingredients', component: IngredientComponent, canActivate: [AdminGuard]},
  { path: 'error', component: ErrorComponent},
  { path: 'login', component: LoginComponent},
  { path: 'signup',component: RegisterComponent},
  { path: 'confirm/:token',component: ConfirmComponent},
  { path: '', redirectTo: 'accueil', pathMatch: 'full'},
  { path: 'recettes', redirectTo: 'accueil', pathMatch: 'full'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }