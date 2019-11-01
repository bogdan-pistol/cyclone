import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { SelectionsService } from "./../../shared/services/selections.service";
import { FilesService } from "./../files.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ICONS } from "./../../shared/models/icons";
import { MatDialog } from "@angular/material/dialog";
import { ModalComponent } from "./../../shared/modal/modal.component";
import { NotificationsService } from "angular2-notifications";
@Component({
  selector: "app-list-files",
  templateUrl: "./list-files.component.html",
  styleUrls: ["./list-files.component.scss"]
})
export class ListFilesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listFiles: any[];
  listFilesForm: FormGroup;
  icons = ICONS;
  name: string;
  crtSelection: any;
  crtCountry: any;
  constructor(
    public dialog: MatDialog,
    private selectionsService: SelectionsService,
    private formBuilder: FormBuilder,
    private filesService: FilesService,
    private notificationsService: NotificationsService
  ) {
    this.listFilesForm = this.formBuilder.group({
      selectedFile: ["", Validators.required]
    });
  }

  ngOnInit() {
    this.getCrtCountry();
  }

  getCrtCountry() {
    this.selectionsService.onChangeCountrySubject.subscribe(newCountry => {
      this.crtCountry = newCountry;
      this.getListFiles(this.selectionsService.crtSelection);
    });
  }

  getListFiles(crtSelection) {
    this.filesService.getFiles(crtSelection).subscribe(response => {
      this.listFiles = <any[]>response;
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
        this.addNewFileName(result);
      }
    });
  }

  addNewFileName(newFileName) {
    this.filesService.addNewFileName(newFileName).subscribe(
      response => {
        this.notificationsService.success(
          "New file name was added succesfully"
        );
        this.getListFiles(JSON.parse(this.crtSelection));
      },
      error => {
        this.notificationsService.error(error.error.message);
      }
    );
  }

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeFileSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listFilesForm.valid);
    }, 200);
  }
}
