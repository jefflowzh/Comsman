import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllSearchResultsComponent } from './view-all-search-results.component';

describe('ViewAllSearchResultsComponent', () => {
  let component: ViewAllSearchResultsComponent;
  let fixture: ComponentFixture<ViewAllSearchResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllSearchResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllSearchResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
