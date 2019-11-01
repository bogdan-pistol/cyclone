import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListNamespacesComponent } from "./list-namespaces/list-namespaces.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "./../shared/material.module";
@NgModule({
  declarations: [ListNamespacesComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [ListNamespacesComponent]
})
export class NamespacesModule {}
