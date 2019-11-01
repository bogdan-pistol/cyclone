import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { SelectionsService } from "./../../shared/services/selections.service";
import { ModalComponent } from "./../../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";
import { NamespaceService } from "./../namespace.service";
import { ICONS } from "./../../shared/models/icons";
import { NotificationsService } from "angular2-notifications";
@Component({
  selector: "app-list-namespaces",
  templateUrl: "./list-namespaces.component.html",
  styleUrls: ["./list-namespaces.component.scss"]
})
export class ListNamespacesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listNamespacesForm: FormGroup;
  listNamespaces: any[];
  crtSelection: any;
  crtTenant: any;
  name: string;
  icons = ICONS;

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService,
    private namespaceService: NamespaceService,
    private notificationsService: NotificationsService
  ) {
    this.listNamespacesForm = this.formBuilder.group({
      selectedNameSpace: ["", Validators.required]
    });
  }

  ngOnInit() {
    this.getCrtTenant();
  }

  getCrtTenant() {
    this.selectionsService.onChangeTenantSubject.subscribe(newTenant => {
      this.crtTenant = newTenant;

      this.getNamespaces(this.crtTenant);
    });
  }

  getNamespaces(crtTenant) {
    this.namespaceService.getNamespaces(crtTenant).subscribe(response => {
      this.listNamespaces = <any[]>response;
    });
  }

  removeNamespace(event, nameSpaceName) {
    event.preventDefault();
    event.stopPropagation();
    if (confirm("Are you sure?")) {
      this.namespaceService.removeNamespace(nameSpaceName).subscribe(
        response => {
          this.notificationsService.success(
            "Namespace was removed succesfully"
          );
          this.getNamespaces(this.crtTenant);
        },

        error => {
          this.notificationsService.error(
            "Something was wrong. Try again later"
          );
        }
      );
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: "250px",
      data: { name: this.name }
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.name = result;
      if (result) {
        this.addNewNameSpace(result);
      }
    });
  }

  addNewNameSpace(newNameSpace) {
    this.namespaceService.addNewNameSpace(newNameSpace).subscribe(
      response => {
        this.notificationsService.success(
          "New namespace was added succesfully"
        );
        this.listNamespaces.push(response);
      },
      error => {
        this.notificationsService.error(error.error.message);
      }
    );
  }

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeNamespaceSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listNamespacesForm.valid);
    }, 100);
  }
}
