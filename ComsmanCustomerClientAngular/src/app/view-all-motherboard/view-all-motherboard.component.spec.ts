import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllMotherboardComponent } from './view-all-motherboard.component';

describe('ViewAllMotherboardComponent', () => {
  let component: ViewAllMotherboardComponent;
  let fixture: ComponentFixture<ViewAllMotherboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllMotherboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllMotherboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
