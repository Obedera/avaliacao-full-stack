import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferCrudComponent } from './transfer-crud.component';

describe('TransferCrudComponent', () => {
  let component: TransferCrudComponent;
  let fixture: ComponentFixture<TransferCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
