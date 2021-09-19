import { TestBed } from '@angular/core/testing';

import { BookUnitServiceService } from './book-unit-service.service';

describe('BookUnitServiceService', () => {
  let service: BookUnitServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookUnitServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
