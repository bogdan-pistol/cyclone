import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { SelectionsService } from "../shared/services/selections.service";

import { ModalComponent } from "./../shared/modal/modal.component";
import { MatDialog } from "@angular/material/dialog";


@Component({
  selector: "app-list-namespaces",
  templateUrl: "./list-namespaces.component.html",
  styleUrls: ["./list-namespaces.component.scss"]
})
export class ListNamespacesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listNamespaces: FormGroup;
  crtSelection: any;

  name: string;
  namespaces: any[] = [
    {
      name: "Dev",
      icon: "developer_mode"
    },
    {
      name: "Prod",
      icon: "storage"
    },
    {
      name: "Prod qualif",
      icon: "pages"
    }
  ];

  constructor(

    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listNamespaces = this.formBuilder.group({
      selectedNameSpace: ["", Validators.required]
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
        this.namespaces.push({ name: result, icon: "crop_free" });
      }
    });
  }


  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeNamespaceSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listNamespaces.valid);
    }, 100);
  }
}
