import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllPeripheralsComponent } from './view-all-peripherals.component';

describe('ViewAllPeripheralsComponent', () => {
  let component: ViewAllPeripheralsComponent;
  let fixture: ComponentFixture<ViewAllPeripheralsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllPeripheralsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllPeripheralsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
