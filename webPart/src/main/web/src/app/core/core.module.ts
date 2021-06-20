import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from "../app-routing.module";
import { FormsModule } from "@angular/forms";
import { OrderByPipe } from "../sort.pipe";
import { MyFilterPipe } from "../filter.pipe";
import { NumberPipe } from "../number.pipe";


import { RecetteTypeService, RecetteNameService, TransmissionService, MenuService, IngredientService, RecetteService, ConfigService, RecetteUsedService, IngredientUsedService } from "../services";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MesRecettesComponent, AccueilComponent, UtilisateurComponent, ConfirmComponent, RegisterComponent, LoginComponent, ListeComponent, RecetteComponent, RecettesComponent, IngredientComponent, MenuComponent, ModalContent, ErrorComponent } from "../pages";
import { NgDragDropModule } from 'ng-drag-drop';
import { ImageCropperModule } from 'ngx-image-cropper';

import { httpInterceptorProviders } from '../../auth/auth-interceptor';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ImageCropperModule,
    NgDragDropModule.forRoot()
  ],
  exports: [
    AppRoutingModule
  ],
  providers: [
    RecetteTypeService,
    RecetteNameService,
    TransmissionService,
    IngredientService,
    IngredientUsedService,
    MenuService,
    RecetteService,
    RecetteUsedService,
    ConfigService,
    httpInterceptorProviders
  ],
  declarations: [
    MesRecettesComponent,
    AccueilComponent,
    UtilisateurComponent,
    ConfirmComponent,
    RegisterComponent,
    LoginComponent,
    ListeComponent,
    RecettesComponent,
    RecetteComponent,
    IngredientComponent,
    MenuComponent,
    ErrorComponent,
    ModalContent,
    OrderByPipe,
    MyFilterPipe,
    NumberPipe
  ]
})
export class CoreModule { }
