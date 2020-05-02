import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmateurSetBuildPageComponent } from './amateur-set-build-page.component';

describe('AmateurSetBuildPageComponent', () => {
  let component: AmateurSetBuildPageComponent;
  let fixture: ComponentFixture<AmateurSetBuildPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmateurSetBuildPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmateurSetBuildPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
