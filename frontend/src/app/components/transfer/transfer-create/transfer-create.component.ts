import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { TransferConfirmComponent } from '../modal/transfer-confirm/transfer-confirm.component';
import { Transfer } from '../transfer.model';
import { TransferService } from '../transfer.service';

@Component({
  selector: 'app-transfer-create',
  templateUrl: './transfer-create.component.html',
  styleUrls: ['./transfer-create.component.scss']
})
export class TransferCreateComponent implements OnInit {
  constructor(
    private transferService: TransferService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private router: Router

  ) { }

  formTransfer: FormGroup = this.formBuilder.group({
    contaOrigem: [''],
    contaDestino: [''],
    valor: [''],
    dataAgendamento: [{ value: moment().format('DD/MM/YYYY'), disabled: true }],
    dataTransferencia: [''],
  });



  ngOnInit(): void {
  }


  builderDTOTransfer(form: any): Transfer {
    return {
      contaOrigem: form.contaOrigem,
      contaDestino: form.contaDestino,
      valor: Number(this.formatNumber(form.valor)),
      dataAgendamento: moment().format('YYYY-MM-DD'),
      dataTransferencia: moment(this.maskDate(form.dataTransferencia), 'DD/MM/YYYY').format('YYYY-MM-DD'),
    }
  }




  resumeTransfer(): void {
    console.log(this.validForm())
    if (this.validForm()) {
      this.transferService.resume(this.builderDTOTransfer(this.formTransfer.value)).subscribe((resp) => {
        const dialogRef = this.dialog.open(TransferConfirmComponent, {
          width: "50%",
          height: "auto",
          panelClass: ["slideInUp"],
          maxWidth: "unset",
          data: {
            transfer: resp
          },
        });
        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.transferService.confirm(this.builderDTOTransfer(this.formTransfer.value)).subscribe((result) => {
              this.transferService.showMessage("Transferencia registrada!");
              this.router.navigate([''])


            })

          }
        })
      })
    }


  }

  validForm(): boolean {
    if (!moment(this.maskDate(this.formTransfer.value.dataTransferencia), 'DD/MM/YYYY').isValid()) {
      this.formTransfer.controls['dataTransferencia'].setErrors({ 'incorrect': true })
      this.transferService.showMessage("Data de transferencia invalida!");
      return false
    }
    return true

  }

  handleDataTransferencia(): void {
    this.formTransfer.patchValue({ dataTransferencia: this.maskDate(this.formTransfer.value.dataTransferencia) })
  }

  //Formata o numero para o formato US e corrige o numero de decimais de 3 para 2
  formatNumber(val: string): string {
    const valArr = val.replace(' ', '').split('').filter((x: any) => !isNaN(x))
    // usando operador unary plus para converter para numero e remover os 0 a esquerda
    const valNumber = +valArr.join('');
    const valProcessed = valNumber.toString()

    if (!valProcessed.length) {
      return '0'
    } else if (valProcessed.length < 3) {
      const finalValue = valProcessed.replace(/^(\d+)/, '0.$1')
      return finalValue
    } else {
      const finalValue = valProcessed.replace(/^(\d+)(\d{2})/, '$1.$2')
      return finalValue
    }
  }

  maskDate(value: string): string {
    let v = value.replace(/\D/g, '').slice(0, 10);
    if (v.length >= 5) {
      return `${v.slice(0, 2)}/${v.slice(2, 4)}/${v.slice(4)}`;
    }
    else if (v.length >= 3) {
      return `${v.slice(0, 2)}/${v.slice(2)}`;
    }
    return v
  }

}
