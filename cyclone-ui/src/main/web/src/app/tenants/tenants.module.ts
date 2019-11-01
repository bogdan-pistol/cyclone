import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListTenantsComponent } from "./list-tenants/list-tenants.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "./../shared/material.module";
import { TenantsService } from "./tenants.service";
@NgModule({
  declarations: [ListTenantsComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [ListTenantsComponent],
  providers: [TenantsService]
})
export class TenantsModule {}
