import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YourPatientsComponent } from './your-patients.component';

describe('YourPatientsComponent', () => {
  let component: YourPatientsComponent;
  let fixture: ComponentFixture<YourPatientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YourPatientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YourPatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
