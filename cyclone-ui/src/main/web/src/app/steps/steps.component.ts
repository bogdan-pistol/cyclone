import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap } from "@angular/router";
import { keyframes } from "@angular/animations";
@Component({
  selector: "app-steps",
  templateUrl: "./steps.component.html",
  styleUrls: ["./steps.component.scss"]
})
export class StepsComponent implements OnInit {
  private queryParamMap: any;
  crtStep: number;
  showBack: boolean;
  constructor(private activatedRoute: ActivatedRoute) {
    this.crtStep = 1;
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParamMap => {
      this.queryParamMap = queryParamMap;
      this.checkQueryParameters(this.queryParamMap.params);
    });
  }

  checkQueryParameters(params) {
    const steps = Object.keys(params);

    if (steps.length === 0) {
      this.crtStep = 1;
      this.showBack = false;
    }
    if (steps.length === 1 && steps.indexOf("namespace") === 0) {
      this.crtStep = 2;
      this.showBack = true;
    }
    if (steps.length === 2 && steps.indexOf("country") === 1) {
      this.crtStep = 3;
      this.showBack = true;
    }
  }

  goBack() {
    window.history.back();
  }
}
