import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { SelectionsService } from "../shared/services/selections.service";

@Component({
  selector: "app-list-namespaces",
  templateUrl: "./list-namespaces.component.html",
  styleUrls: ["./list-namespaces.component.scss"]
})
export class ListNamespacesComponent implements OnInit {
  @Output() validateFormEmitter = new EventEmitter();
  listNamespaces: FormGroup;
  crtSelection: any;
  namespaces: any[] = [
    {
      title: "Dev",
      description:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s",
      icon: "developer_mode"
    },
    {
      title: "Prod",
      description:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s",
      icon: "storage"
    },
    {
      title: "Prod qualif",
      description:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s",
      icon: "pages"
    }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private selectionsService: SelectionsService
  ) {
    this.listNamespaces = this.formBuilder.group({
      selectedNameSpace: ["", Validators.required]
    });
  }

  ngOnInit() {}

  validateCrtStep(selectedValue) {
    this.selectionsService.onChangeNamespaceSubject.next(selectedValue);
    setTimeout(() => {
      this.validateFormEmitter.emit(this.listNamespaces.valid);
    }, 100);
  }
}
