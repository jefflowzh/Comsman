import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllSsdComponent } from './view-all-ssd.component';

describe('ViewAllSsdComponent', () => {
  let component: ViewAllSsdComponent;
  let fixture: ComponentFixture<ViewAllSsdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllSsdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllSsdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
