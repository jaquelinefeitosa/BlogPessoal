import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasasGotComponent } from './casas-got.component';

describe('CasasGotComponent', () => {
  let component: CasasGotComponent;
  let fixture: ComponentFixture<CasasGotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasasGotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CasasGotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
