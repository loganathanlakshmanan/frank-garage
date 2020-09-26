import {Product} from './product';

describe('Product', () => {
  it('should create an instance', () => {
    expect(new Product(
      1, 'Volkswagen', 'Jetta III', 1995, 12947.52, true, '2018-09-18', '1'
    )).toBeTruthy();
  });
});
