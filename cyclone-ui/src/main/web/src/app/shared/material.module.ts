import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatInputModule } from "@angular/material/input";
import { MatStepperModule } from "@angular/material/stepper";
import { MatRadioModule } from "@angular/material/radio";
import { MatDialogModule } from "@angular/material/dialog";
import { ModalComponent } from "./modal/modal.component";
import { FormsModule } from "@angular/forms";
@NgModule({
  declarations: [ModalComponent],
  imports: [
    FormsModule,
    CommonModule,
    MatButtonModule,
    MatExpansionModule,
    MatInputModule,
    MatStepperModule,
    MatRadioModule,
    MatDialogModule
  ],
  exports: [
    MatButtonModule,
    MatExpansionModule,
    MatInputModule,
    MatStepperModule,
    MatRadioModule,
    MatDialogModule,
    ModalComponent
  ]
})
export class MaterialModule {}
