import { NgModule, Component } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { ListCountriesComponent } from "./countries/list-countries/list-countries.component";
import { ListFilesComponent } from "./files/list-files/list-files.component";
import { ListNamespacesComponent } from "./namespaces/list-namespaces/list-namespaces.component";
const routes: Routes = [
  {
    path: "namespaces",
    component: ListNamespacesComponent,
    children: [
      {
        path: ":id",
        component: ListCountriesComponent,
        children: [
          {
            path: ":id",
            component: ListFilesComponent
          }
        ]
      }
    ]
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "countries",
    component: ListCountriesComponent
  },
  {
    path: "list-config",
    component: ListFilesComponent
  },
  {
    path: "**",
    redirectTo: "home"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
