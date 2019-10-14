import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Country } from "./../shared/models/country-model";
import { SelectionsService } from "../shared/services/selections.service";

@Component({
  selector: "app-list-countries",
  templateUrl: "./list-countries.component.html",
  styleUrls: ["./list-countries.component.scss"]
})
export class ListCountriesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listCountries: FormGroup;

  countries: Country[] = [
    { id: "ro", name: "Romania" },
    { id: "es", name: "Spain" },
    { id: "fr", name: "France" },
    { id: "gb", name: "Great Britain" },
    { id: "pl", name: "Poland" },
    { id: "be", name: "Belgium" },
    { id: "md", name: "Moldova" },
    { id: "sk", name: "Slovakia" },
    { id: "lu", name: "Luxembourg" }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listCountries = this.formBuilder.group({
      selectedCountry: ["", Validators.required]
    });
  }

  ngOnInit() {}

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeCountrySubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listCountries.valid);
    }, 200);
  }
}
