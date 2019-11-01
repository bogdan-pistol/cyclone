import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { HeaderComponent } from "./header/header.component";
import { LeftMenuComponent } from "./left-menu/left-menu.component";
import { MaterialModule } from "./shared/material.module";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { StepperComponent } from "./stepper/stepper.component";
import { SharedModule } from "./shared/modules/shared.module";
import { HttpService } from "./shared/services/http.service";
import { SimpleNotificationsModule } from "angular2-notifications";
import { ModalComponent } from "./shared/modal/modal.component";
import { TenantsModule } from "./tenants/tenants.module";
import { NamespacesModule } from "./namespaces/namespaces.module";
import { CountriesModule } from "./countries/countries.module";
import { FilesModule } from "./files/files.module";
import { PropertiesModule } from "./properties/properties.module";
import { HttpClientModule } from "@angular/common/http";
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LeftMenuComponent,
    StepperComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,
    AppRoutingModule,
    MaterialModule,
    NgbModule,
    NamespacesModule,
    TenantsModule,
    CountriesModule,
    FilesModule,
    PropertiesModule,
    SimpleNotificationsModule.forRoot()
  ],
  entryComponents: [ModalComponent],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule {}
