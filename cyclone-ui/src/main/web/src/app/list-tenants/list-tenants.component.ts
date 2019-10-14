import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Tenant } from "./../shared/models/tenant";
import { SelectionsService } from "../shared/services/selections.service";

@Component({
  selector: "app-list-tenants",
  templateUrl: "./list-tenants.component.html",
  styleUrls: ["./list-tenants.component.scss"]
})
export class ListTenantsComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listTenants: FormGroup;

  tenants: Tenant[] = [
    { name: "Malima Portal", icon: "crop_free" },
    { name: "Malima Core", icon: "filter_center_focus" }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listTenants = this.formBuilder.group({
      selectedTenant: ["", Validators.required]
    });
  }

  ngOnInit() {}

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeTenantSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listTenants.valid);
    }, 200);
  }
}
