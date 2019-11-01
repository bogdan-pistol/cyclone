import { Injectable } from "@angular/core";
import { HttpService } from "./../shared/services/http.service";
import { devIP } from "./../../environments/environment";
import { map } from "rxjs/operators";
import { pipe } from "rxjs";
import { SelectionsService } from "./../shared/services/selections.service";
@Injectable({
  providedIn: "root"
})
export class CountriesService {
  apiCountries: string;

  constructor(
    private httpService: HttpService,
    private selectionsService: SelectionsService
  ) {}

  getCountries(crtTenant, crtNamespace) {
    this.apiCountries = devIP + "tenants/";
    this.apiCountries += crtTenant + "/namespaces/" + crtNamespace + "/apps";
    return this.httpService
      .get(this.apiCountries)
      .pipe(map(response => response));
  }

  removeCountry(countryName) {
    this.apiCountries = devIP + "tenants/";
    return this.httpService.delete(
      this.apiCountries
        .concat(this.selectionsService.crtSelection.tenant)
        .concat("/namespaces/")
        .concat(this.selectionsService.crtSelection.namespace)
        .concat("/apps/")
        .concat(countryName)
        .concat("/archive")
    );
  }

  addNewCountry(newCountry) {
    return this.httpService.post(this.apiCountries, newCountry);
  }
}
