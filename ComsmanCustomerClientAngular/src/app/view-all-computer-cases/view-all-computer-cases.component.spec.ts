import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllComputerCasesComponent } from './view-all-computer-cases.component';

describe('ViewAllComputerCasesComponent', () => {
  let component: ViewAllComputerCasesComponent;
  let fixture: ComponentFixture<ViewAllComputerCasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllComputerCasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllComputerCasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
