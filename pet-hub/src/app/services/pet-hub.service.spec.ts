import { TestBed } from '@angular/core/testing';

import { PetHubService } from './pet-hub.service';

describe('PetHubService', () => {
  let service: PetHubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetHubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
