import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllAirCoolerComponent } from './view-all-air-cooler.component';

describe('ViewAllAirCoolerComponent', () => {
  let component: ViewAllAirCoolerComponent;
  let fixture: ComponentFixture<ViewAllAirCoolerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllAirCoolerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllAirCoolerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
