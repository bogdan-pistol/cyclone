import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListPropertiesComponent } from "./list-properties/list-properties.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "./../shared/material.module";
@NgModule({
  declarations: [ListPropertiesComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [ListPropertiesComponent]
})
export class PropertiesModule {}
