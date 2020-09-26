import {CurrencyPipe, formatCurrency, getCurrencySymbol} from "@angular/common";
import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'dutchCurrency'
})
export class DutchCurrencyPipe extends CurrencyPipe implements PipeTransform {
  constructor() {
    super('nl-Nl');
  }

  transform(
    value: number,
    currencyCode: string = 'EUR',
    display:
      | 'code'
      | 'symbol'
      | 'symbol-narrow'
      | string
      | boolean = 'symbol',
    digitsInfo: string = '3.2-2',
    locale: string = 'nl-NL'
  ): string | null {
    return formatCurrency(
      value,
      locale,
      getCurrencySymbol(currencyCode, 'wide'),
      currencyCode,
      digitsInfo
    );
  }

}
