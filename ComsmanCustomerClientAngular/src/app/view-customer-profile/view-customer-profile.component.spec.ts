import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCustomerProfileComponent } from './view-customer-profile.component';

describe('ViewCustomerProfileComponent', () => {
  let component: ViewCustomerProfileComponent;
  let fixture: ComponentFixture<ViewCustomerProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCustomerProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCustomerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
