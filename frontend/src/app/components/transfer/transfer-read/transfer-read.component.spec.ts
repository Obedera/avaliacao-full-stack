import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferReadComponent } from './transfer-read.component';

describe('TransferReadComponent', () => {
  let component: TransferReadComponent;
  let fixture: ComponentFixture<TransferReadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferReadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
