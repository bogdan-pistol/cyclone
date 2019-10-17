import { Component, OnInit, Output, EventEmitter, Inject } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Tenant } from "./../shared/models/tenant";
import { SelectionsService } from "../shared/services/selections.service";
import { ModalComponent } from "./../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";


@Component({
  selector: "app-list-tenants",
  templateUrl: "./list-tenants.component.html",
  styleUrls: ["./list-tenants.component.scss"]
})
export class ListTenantsComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listTenants: FormGroup;
  name: string;

  tenants: Tenant[] = [
    { name: "Malima Portal", icon: "crop_free" },
    { name: "Malima Core", icon: "filter_center_focus" }
  ];

  constructor(

    public dialog: MatDialog,

    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listTenants = this.formBuilder.group({
      selectedTenant: ["", Validators.required]
    });
  }

  ngOnInit() {}


  openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: "250px",
      data: { name: this.name }
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.name = result;
      if (result) {
        this.tenants.push({ name: result, icon: "crop_free" });
      }
    });
  }


  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeTenantSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listTenants.valid);
    }, 200);
  }
}
