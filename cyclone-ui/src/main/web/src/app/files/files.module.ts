import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListFilesComponent } from "./list-files/list-files.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "./../shared/material.module";

@NgModule({
  declarations: [ListFilesComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [ListFilesComponent]
})
export class FilesModule {}
