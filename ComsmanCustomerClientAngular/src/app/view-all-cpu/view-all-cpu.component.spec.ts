import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllCPUComponent } from './view-all-cpu.component';

describe('ViewAllCPUComponent', () => {
  let component: ViewAllCPUComponent;
  let fixture: ComponentFixture<ViewAllCPUComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllCPUComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllCPUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
