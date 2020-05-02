import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllPowerSupplyComponent } from './view-all-power-supply.component';

describe('ViewAllPowerSupplyComponent', () => {
  let component: ViewAllPowerSupplyComponent;
  let fixture: ComponentFixture<ViewAllPowerSupplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllPowerSupplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllPowerSupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
