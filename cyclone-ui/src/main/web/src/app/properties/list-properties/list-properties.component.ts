import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { SelectionsService } from "./../../shared/services/selections.service";
import { PropertiesService } from "./../properties.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatInputModule } from "@angular/material/input";
import { NotificationsService } from "angular2-notifications";

@Component({
  selector: "app-list-properties",
  templateUrl: "./list-properties.component.html",
  styleUrls: ["./list-properties.component.scss"]
})
export class ListPropertiesComponent implements OnInit {
  listProperties: any[];
  crtFile: string;
  constructor(
    private selectionsService: SelectionsService,
    private formBuilder: FormBuilder,
    private propertiesService: PropertiesService,
    private notificationsService: NotificationsService
  ) {}

  ngOnInit() {
    this.getCrtFile();
  }

  getCrtFile() {
    this.selectionsService.onChangeFileSubject.subscribe(newFile => {
      this.crtFile = newFile;
      this.getListProperties(this.selectionsService.crtSelection);
    });
  }

  getListProperties(crtSelection) {
    this.propertiesService.getProperties(crtSelection).subscribe(response => {
      this.listProperties = <any[]>response;
    });
  }
}
