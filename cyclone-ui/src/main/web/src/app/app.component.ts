import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  title = "Cyclone";
  fullPage: boolean;
  public options = {
    position: ["top", "right"],
    timeOut: 3000,
    lastOnBottom: true
  };
  ngOnInit() {
    this.fullPage = window.innerWidth < 768 ? false : true;
  }
  onResize(event) {
    if (event.target.innerWidth < 768) {
      document.getElementById("sidenav").classList.remove("expandedLeftMenu");
      document.getElementById("sidenav").classList.add("collapsedLeftMenu");
      document.getElementById("main").classList.remove("expandedMain");
      document.getElementById("main").classList.add("collapsedMain");
    } else {
      document.getElementById("sidenav").classList.remove("collapsedLeftMenu");
      document.getElementById("sidenav").classList.add("expandedLeftMenu");
      document.getElementById("main").classList.remove("collapsedMain");
      document.getElementById("main").classList.add("expandedMain");
    }
  }
}
