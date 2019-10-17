import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Country } from "./../shared/models/country-model";
import { SelectionsService } from "../shared/services/selections.service";
import { ModalComponent } from "./../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";
@Component({
  selector: "app-list-countries",
  templateUrl: "./list-countries.component.html",
  styleUrls: ["./list-countries.component.scss"]
})
export class ListCountriesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listCountries: FormGroup;
  name: string;
  countries: Country[] = [
    { name: "Romania", icon: "ro" },
    { name: "Spain", icon: "es" },
    { name: "France", icon: "fr" },
    { name: "Great Britain", icon: "gb" },
    { name: "Poland", icon: "pl" },
    { name: "Belgium", icon: "be" },
    { name: "Moldova", icon: "md" },
    { name: "Slovakia", icon: "sk" },
    { name: "Luxembourg", icon: "lu" }
  ];

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listCountries = this.formBuilder.group({
      selectedCountry: ["", Validators.required]
    });
  }

  ngOnInit() {}

  openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: "250px",
      data: { name: this.name }
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.name = result;
      if (result) {
        this.countries.push({ name: result, icon: "crop_free" });
      }
    });
  }

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeCountrySubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listCountries.valid);
    }, 200);
  }
}
