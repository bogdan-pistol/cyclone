import { Injectable } from "@angular/core";
import { Selection } from "./../models/selection";
import { Subject } from "rxjs";
@Injectable({
  providedIn: "root"
})
export class SelectionsService {
  crtSelection: Selection = { tenant: "", namespace: "", country: "" };
  onChangeTenantSubject = new Subject<string>();
  onChangeNamespaceSubject = new Subject<string>();
  onChangeCountrySubject = new Subject<string>();
  constructor() {
    this.updateCrtSelectionObject();
    this.updateCrtNameSpace();
    this.updateCrtCountry();
  }

  updateCrtSelectionObject() {
    this.onChangeTenantSubject.subscribe(
      newTenant => (this.crtSelection.tenant = newTenant)
    );
  }

  updateCrtNameSpace() {
    this.onChangeNamespaceSubject.subscribe(
      newNamespace => (this.crtSelection.namespace = newNamespace)
    );
  }

  updateCrtCountry() {
    this.onChangeCountrySubject.subscribe(
      newCountry => (this.crtSelection.country = newCountry)
    );
  }
}
