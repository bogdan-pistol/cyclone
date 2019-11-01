import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Country } from "./../../shared/models/country-model";
import { SelectionsService } from "./../../shared/services/selections.service";
import { ModalComponent } from "./../../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";
import { CountriesService } from "./../countries.service";
import { ICONS } from "./../../shared/models/icons";
import { NotificationsService } from "angular2-notifications";
@Component({
  selector: "app-list-countries",
  templateUrl: "./list-countries.component.html",
  styleUrls: ["./list-countries.component.scss"]
})
export class ListCountriesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listCountriesForm: FormGroup;
  listCountries: any[];
  crtTenant: string;
  crtNamespace: string;
  name: string;
  icons = ICONS;

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService,
    private countriesService: CountriesService,
    private notificationsService: NotificationsService
  ) {
    this.listCountriesForm = this.formBuilder.group({
      selectedCountry: ["", Validators.required]
    });
  }

  ngOnInit() {
    this.getCrtTenant();
  }

  getCrtTenant() {
    this.selectionsService.onChangeTenantSubject.subscribe(newTenant => {
      this.crtTenant = newTenant;

      this.getCrtNamespace();
    });
  }

  getCrtNamespace() {
    this.selectionsService.onChangeNamespaceSubject.subscribe(newNamespace => {
      this.crtNamespace = newNamespace;

      this.getCountries(this.crtTenant, this.crtNamespace);
    });
  }

  removeCountry(event, countryName) {
    event.preventDefault();
    event.stopPropagation();
    if (confirm("Are you sure?")) {
      this.countriesService.removeCountry(countryName).subscribe(
        response => {
          this.notificationsService.success("Country was removed succesfully");
          this.getCountries(this.crtTenant, this.crtNamespace);
        },

        error => {
          this.notificationsService.error(
            "Something was wrong. Try again later"
          );
        }
      );
    }
  }

  getCountries(crtTenant, crtNamespace) {
    this.countriesService
      .getCountries(crtTenant, crtNamespace)
      .subscribe(response => {
        this.listCountries = <any[]>response;
      });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: "250px",
      data: { name: this.name }
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.name = result;
      if (result) {
        // this.countries.push({ name: result, icon: "crop_free" });
        this.addNewCountry(result);
      }
    });
  }

  addNewCountry(newCountry) {
    this.countriesService.addNewCountry(newCountry).subscribe(
      response => {
        this.notificationsService.success("New country was added succesfully");
        this.listCountries.push(response);
      },
      error => {
        this.notificationsService.error(error.error.message);
      }
    );
  }

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeCountrySubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listCountriesForm.valid);
    }, 200);
  }
}
