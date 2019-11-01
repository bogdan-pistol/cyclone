import { Injectable } from "@angular/core";
import { HttpService } from "./../shared/services/http.service";
import { devIP } from "./../../environments/environment";
import { SelectionsService } from "./../shared/services/selections.service";
import { map, catchError } from "rxjs/operators";
@Injectable({
  providedIn: "root"
})
export class PropertiesService {
  apiProperties: string;
  constructor(
    private httpService: HttpService,
    private selectionsService: SelectionsService
  ) {}

  getProperties(crtSelection) {
    this.apiProperties = devIP + "tenants/";
    this.apiProperties +=
      crtSelection.tenant +
      "/namespaces/" +
      crtSelection.namespace +
      "/apps/" +
      crtSelection.country +
      "/files/" +
      crtSelection.file +
      "/properties";
    return this.httpService
      .get(this.apiProperties)
      .pipe(map(response => response));
  }
}
