import { Injectable } from "@angular/core";
import { HttpService } from "./../shared/services/http.service";
import { SelectionsService } from "./../shared/services/selections.service";
import { devIP } from "./../../environments/environment";
import { map, catchError } from "rxjs/operators";
import { pipe } from "rxjs";
import { throwError, Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class FilesService {
  apiFiles: string;
  constructor(
    private httpService: HttpService,
    private selectionsService: SelectionsService
  ) {}

  getFiles(crtSelection) {
    this.apiFiles = devIP + "tenants/";
    this.apiFiles +=
      crtSelection.tenant +
      "/namespaces/" +
      crtSelection.namespace +
      "/apps/" +
      crtSelection.country +
      "/files";
    return this.httpService.get(this.apiFiles).pipe(map(response => response));
  }

  addNewFileName(newFilename) {
    return this.httpService.post(this.apiFiles, newFilename).pipe(
      map(response => {
        return response;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
}
