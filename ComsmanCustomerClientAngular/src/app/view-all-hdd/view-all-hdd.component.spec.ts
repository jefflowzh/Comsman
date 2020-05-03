import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllHddComponent } from './view-all-hdd.component';

describe('ViewAllHddComponent', () => {
  let component: ViewAllHddComponent;
  let fixture: ComponentFixture<ViewAllHddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllHddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllHddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
