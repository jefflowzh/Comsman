import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllGPUComponent } from './view-all-gpu.component';

describe('ViewAllGPUComponent', () => {
  let component: ViewAllGPUComponent;
  let fixture: ComponentFixture<ViewAllGPUComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllGPUComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllGPUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
