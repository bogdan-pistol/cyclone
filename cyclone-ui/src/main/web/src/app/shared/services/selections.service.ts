import { Injectable, Output, EventEmitter } from "@angular/core";
import { Selection } from "./../models/selection";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class SelectionsService {
  crtSelection: Selection = {
    tenant: "",
    namespace: "",
    country: "",
    file: ""
  };
  @Output() crtSelectionEmmiter = new EventEmitter();
  onChangeTenantSubject = new Subject<string>();
  onChangeNamespaceSubject = new Subject<string>();
  onChangeCountrySubject = new Subject<string>();
  onChangeFileSubject = new Subject<string>();
  constructor() {
    this.updateCrtSelectionObject();
    this.updateCrtNameSpace();
    this.updateCrtCountry();
    this.updateCrtFile();
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
    this.onChangeCountrySubject.subscribe(newCountry => {
      this.crtSelection.country = newCountry;
      this.crtSelectionEmmiter.emit(JSON.stringify(this.crtSelection));
    });
  }

  updateCrtFile() {
    this.onChangeFileSubject.subscribe(newFile => {
      this.crtSelection.file = newFile;
      this.crtSelectionEmmiter.emit(JSON.stringify(this.crtSelection));
    });
  }
}
