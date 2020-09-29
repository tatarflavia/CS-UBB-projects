import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllConferenceComponent } from './all-conference.component';

describe('AllConferenceComponent', () => {
  let component: AllConferenceComponent;
  let fixture: ComponentFixture<AllConferenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllConferenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
