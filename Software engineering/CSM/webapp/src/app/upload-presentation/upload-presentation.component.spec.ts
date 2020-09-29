import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadPresentationComponent } from './upload-presentation.component';

describe('UploadPresentationComponent', () => {
  let component: UploadPresentationComponent;
  let fixture: ComponentFixture<UploadPresentationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadPresentationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadPresentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
