import { Component, OnInit, Output, EventEmitter, Inject } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Tenant } from "./../../shared/models/tenant";
import { SelectionsService } from "./../../shared/services/selections.service";
import { ModalComponent } from "./../../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";
import { TenantsService } from "./../tenants.service";
import { NotificationsService } from "angular2-notifications";
import { ICONS } from "./../../shared/models/icons";

@Component({
  selector: "app-list-tenants",
  templateUrl: "./list-tenants.component.html",
  styleUrls: ["./list-tenants.component.scss"]
})
export class ListTenantsComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listTenantsForm: FormGroup;
  name: string;
  icons = ICONS;
  rippleColor: string;
  listTenants: any[];

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService,
    private tenantsService: TenantsService,
    private notificationsService: NotificationsService
  ) {
    this.listTenantsForm = this.formBuilder.group({
      selectedTenant: ["", Validators.required]
    });
    this.rippleColor = "pink";
  }

  ngOnInit() {
    this.getTenants();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: "250px",
      data: { name: this.name }
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.name = result;
      if (result) {
        // this.listTenants.push({ name: result.name, icon: "crop_free" });
        this.addNewTenant(result);
      }
    });
  }

  addNewTenant(newTenant) {
    this.tenantsService.addNewTenant(newTenant).subscribe(response => {
      this.notificationsService.success("New tenant was added succesfully");
      this.listTenants.push(response);
    });
  }

  removeTenant(event, tenantName) {
    event.preventDefault();
    event.stopPropagation();
    if (confirm("Are you sure?")) {
      this.tenantsService.removeTenant(tenantName).subscribe(
        response => {
          this.notificationsService.success("Tenant was removed succesfully");
          this.getTenants();
        },

        error => {
          this.notificationsService.error(
            "Something was wrong. Try again later"
          );
        }
      );
    }
  }

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeTenantSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listTenantsForm.valid);
    }, 200);
  }

  getTenants() {
    this.tenantsService.getTenants().subscribe(response => {
      this.listTenants = <any[]>response;
    });
  }
}
