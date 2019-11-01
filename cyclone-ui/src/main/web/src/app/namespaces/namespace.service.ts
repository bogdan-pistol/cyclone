import { Injectable } from "@angular/core";
import { HttpService } from "./../shared/services/http.service";
import { devIP } from "./../../environments/environment";
import { map } from "rxjs/operators";
import { pipe } from "rxjs";
import { SelectionsService } from "./../shared/services/selections.service";

@Injectable({
  providedIn: "root"
})
export class NamespaceService {
  apiNamespace: string;

  constructor(
    private httpService: HttpService,
    private selectionsService: SelectionsService
  ) {}

  getNamespaces(crtTenant) {
    this.apiNamespace = devIP + "tenants/";
    this.apiNamespace += crtTenant + "/namespaces";
    return this.httpService
      .get(this.apiNamespace)
      .pipe(map(response => response));
  }

  removeNamespace(nameSpaceName) {
    this.apiNamespace = devIP + "tenants/";
    return this.httpService.delete(
      this.apiNamespace
        .concat(this.selectionsService.crtSelection.tenant)
        .concat("/namespaces/")
        .concat(nameSpaceName)
        .concat("/archive")
    );
  }

  addNewNameSpace(newNameSpace) {
    return this.httpService.post(this.apiNamespace, newNameSpace);
  }
}
