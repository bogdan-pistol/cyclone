import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListCountriesComponent } from "./list-countries/list-countries.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "./../shared/material.module";

@NgModule({
  declarations: [ListCountriesComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [ListCountriesComponent]
})
export class CountriesModule {}
