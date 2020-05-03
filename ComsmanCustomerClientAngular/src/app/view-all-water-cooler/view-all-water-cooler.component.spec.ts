import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllWaterCoolerComponent } from './view-all-water-cooler.component';

describe('ViewAllWaterCoolerComponent', () => {
  let component: ViewAllWaterCoolerComponent;
  let fixture: ComponentFixture<ViewAllWaterCoolerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllWaterCoolerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllWaterCoolerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
