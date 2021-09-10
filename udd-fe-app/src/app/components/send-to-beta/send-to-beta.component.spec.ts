import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendToBetaComponent } from './send-to-beta.component';

describe('SendToBetaComponent', () => {
  let component: SendToBetaComponent;
  let fixture: ComponentFixture<SendToBetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendToBetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendToBetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
