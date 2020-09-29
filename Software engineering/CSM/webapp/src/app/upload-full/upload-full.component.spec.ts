import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadFullComponent } from './upload-full.component';

describe('UploadFullComponent', () => {
  let component: UploadFullComponent;
  let fixture: ComponentFixture<UploadFullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadFullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
