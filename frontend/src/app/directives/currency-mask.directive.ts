import { Directive, HostListener } from "@angular/core";
import { NgControl } from "@angular/forms";

@Directive({
  selector: "[appCurrencyMask]",
})
export class CurrencyMaskDirective {
  constructor(public ngControl: NgControl) {}

  @HostListener('ngModelChange', ['$event'])
  onModelChange(event: any) {
    this.onInputChange(event);
  }


  dinheiroMask(value: any) {
    const textoEmLista = `${value}`.split("").filter(n => (Number(n) || n == '0')).join('').split(' ').join('').split('')

    switch (textoEmLista.length) {
        case 0:
            return value;
            break;

        case 1:
            return `R$ 0,0${textoEmLista[0]}`;
            break;

        default:
            if (textoEmLista[0] == '0' && textoEmLista.length > 2)
                textoEmLista.shift()
                    ;
            textoEmLista[textoEmLista.length - 2] = `,${textoEmLista[textoEmLista.length - 2]}`;
            if (textoEmLista[0].split('')[0] == ',')
                textoEmLista[0] = `0${textoEmLista[0]}`
                    ;

            if (textoEmLista.join('') != '0,00') {
                if (textoEmLista.length > 5)
                    textoEmLista[textoEmLista.length - 6] = `${textoEmLista[textoEmLista.length - 6]}.`

                    ;
                if (textoEmLista.length > 8)
                    textoEmLista[textoEmLista.length - 9] = `${textoEmLista[textoEmLista.length - 9]}.`
                    ;

                if (textoEmLista.length > 11)
                    textoEmLista[textoEmLista.length - 12] = `${textoEmLista[textoEmLista.length - 12]}.`
                    ;
                return `R$ ${textoEmLista.join("")}`
            } else {
                return ''
            }
            break;
      }
  }

  onInputChange(event: any) {
    interface FormatProps {
      lenght: number;
      pattern: string;
      variables: string;
    }
    
    const formats = <FormatProps[]>[
      { lenght: 0, pattern: '', variables:'0,00'},
      { lenght: 1, pattern:/^(\d{0,1})/, variables: '0,$1' },
      { lenght: 2, pattern:/^(\d{0,2})/, variables: '0,$1' },
      { lenght: 3, pattern:/^(\d{0,1})(\d{0,2})/, variables: '$1,$2' },
      { lenght: 4, pattern:/^(\d{0,2})(\d{0,2})/, variables: '$1,$2' },
      { lenght: 5, pattern:/^(\d{0,3})(\d{0,2})/, variables: '$1,$2' },
      { lenght: 6, pattern:/^(\d{0,1})(\d{0,3})(\d{0,2})/, variables: '$1.$2,$3' },
      { lenght: 7, pattern:/^(\d{0,2})(\d{0,3})(\d{0,2})/, variables: '$1.$2,$3' },
      { lenght: 8, pattern:/^(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2,$3' },
      { lenght: 9, pattern:/^(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3,$4' },
      { lenght: 10, pattern:/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3,$4' },
      { lenght: 11, pattern:/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3,$4' },
      { lenght: 12, pattern:/^(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4,$5' },
      { lenght: 13, pattern:/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4,$5' },
      { lenght: 14, pattern:/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4,$5' },
      { lenght: 15, pattern:/^(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5,$6' },
      { lenght: 16, pattern:/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5,$6' },
      { lenght: 17, pattern:/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5,$6' },
      { lenght: 18, pattern:/^(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6,$7' },
      { lenght: 19, pattern:/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6,$7' },
      { lenght: 20, pattern:/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6,$7' },
      { lenght: 21, pattern:/^(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6.$7,$8' },
      { lenght: 22, pattern:/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6.$7,$8' },
      { lenght: 23, pattern:/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, variables: '$1.$2.$3.$4.$5.$6.$7,$8' },
    ];

    
    let newVal = event.replace(/\D/g, '');
    if (formats[newVal.length]) {
      newVal = newVal.replace(formats[newVal.length].pattern, formats[newVal.length].variables);
    }
    
    this.ngControl.valueAccessor?.writeValue(this.dinheiroMask(event));
  }
}
