import { TestBed } from '@angular/core/testing';

import { ComputerSetService } from './computer-set.service';

describe('ComputerSetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComputerSetService = TestBed.get(ComputerSetService);
    expect(service).toBeTruthy();
  });
});
