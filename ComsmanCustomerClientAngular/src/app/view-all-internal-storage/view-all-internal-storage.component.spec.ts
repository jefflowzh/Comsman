import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllInternalStorageComponent } from './view-all-internal-storage.component';

describe('ViewAllInternalStorageComponent', () => {
  let component: ViewAllInternalStorageComponent;
  let fixture: ComponentFixture<ViewAllInternalStorageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllInternalStorageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllInternalStorageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
