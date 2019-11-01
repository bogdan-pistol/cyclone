import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNamespacesComponent } from './list-namespaces.component';

describe('ListNamespacesComponent', () => {
  let component: ListNamespacesComponent;
  let fixture: ComponentFixture<ListNamespacesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNamespacesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNamespacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
