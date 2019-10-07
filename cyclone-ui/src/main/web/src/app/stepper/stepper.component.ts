import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.scss']
})
export class StepperComponent implements OnInit {
  listCountries: FormGroup;
  listNamespaces: FormGroup;
  listConfig: FormGroup;
  isLinear = true;
  constructor(private formBuilder: FormBuilder) {
    this.listNamespaces = this.formBuilder.group({
      control: ['', Validators.required]
    });
    this.listCountries = this.formBuilder.group({
      control: ['', Validators.required]
    });
    this.listConfig = new FormGroup({});
  }

  ngOnInit() {}

  validateForm(validated, crtForm: FormGroup) {
    if (validated) {
      crtForm.controls['control'].setValue('valid');
    }
  }
}
