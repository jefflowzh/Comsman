import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllRAMComponent } from './view-all-ram.component';

describe('ViewAllRAMComponent', () => {
  let component: ViewAllRAMComponent;
  let fixture: ComponentFixture<ViewAllRAMComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllRAMComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllRAMComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
