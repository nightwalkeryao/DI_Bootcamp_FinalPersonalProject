import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeBlocksComponent } from './home-blocks.component';

describe('HomeBlocksComponent', () => {
  let component: HomeBlocksComponent;
  let fixture: ComponentFixture<HomeBlocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeBlocksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeBlocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
