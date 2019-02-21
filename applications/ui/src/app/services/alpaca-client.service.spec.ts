import { TestBed } from '@angular/core/testing';

import { AlpacaClientService } from './alpaca-client.service';

describe('AlpacaClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AlpacaClientService = TestBed.get(AlpacaClientService);
    expect(service).toBeTruthy();
  });
});
