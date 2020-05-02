import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllCPUCoolerComponent } from './view-all-cpucooler.component';

describe('ViewAllCPUCoolerComponent', () => {
  let component: ViewAllCPUCoolerComponent;
  let fixture: ComponentFixture<ViewAllCPUCoolerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllCPUCoolerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllCPUCoolerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
