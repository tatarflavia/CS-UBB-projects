import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcmemberRegistrationConferenceComponent } from './pcmember-registration-conference.component';

describe('PcmemberRegistrationConferenceComponent', () => {
  let component: PcmemberRegistrationConferenceComponent;
  let fixture: ComponentFixture<PcmemberRegistrationConferenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcmemberRegistrationConferenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcmemberRegistrationConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
