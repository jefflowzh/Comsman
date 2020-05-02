import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmateurSetMainPageComponent } from './amateur-set-main-page.component';

describe('AmateurSetMainPageComponent', () => {
  let component: AmateurSetMainPageComponent;
  let fixture: ComponentFixture<AmateurSetMainPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmateurSetMainPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmateurSetMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
