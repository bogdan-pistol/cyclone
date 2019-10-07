import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { LeftMenuComponent } from './left-menu/left-menu.component';
import { MaterialModule } from './shared/material.module';
import { ListCountriesComponent } from './list-countries/list-countries.component';
import { ListNamespacesComponent } from './list-namespaces/list-namespaces.component';
import { ListConfigComponent } from './list-config/list-config.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StepsComponent } from './steps/steps.component';
import { StepperComponent } from './stepper/stepper.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LeftMenuComponent,
    ListCountriesComponent,
    ListNamespacesComponent,
    ListConfigComponent,
    StepsComponent,
    StepperComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
