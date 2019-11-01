import { Injectable } from "@angular/core";
import { HttpService } from "./../shared/services/http.service";
import { devIP } from "./../../environments/environment";
import { map } from "rxjs/operators";
import { pipe } from "rxjs";
@Injectable({
  providedIn: "root"
})
export class TenantsService {
  apiTenants = devIP + "tenants";

  constructor(private httpService: HttpService) {}

  getTenants() {
    return this.httpService
      .get(this.apiTenants)
      .pipe(map(response => response));
  }

  addNewTenant(newTenant) {
    return this.httpService.post(this.apiTenants, newTenant);
  }

  removeTenant(tenantName) {
    return this.httpService.delete(
      this.apiTenants.concat("/").concat(tenantName)
    );
  }
}
