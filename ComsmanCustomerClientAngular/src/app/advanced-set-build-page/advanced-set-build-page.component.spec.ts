import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedSetBuildPageComponent } from './advanced-set-build-page.component';

describe('AdvancedSetBuildPageComponent', () => {
  let component: AdvancedSetBuildPageComponent;
  let fixture: ComponentFixture<AdvancedSetBuildPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedSetBuildPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedSetBuildPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
