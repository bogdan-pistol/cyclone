import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { ListConfigComponent } from "./list-files.component";

describe("ListConfigComponent", () => {
  let component: ListConfigComponent;
  let fixture: ComponentFixture<ListConfigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListConfigComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
