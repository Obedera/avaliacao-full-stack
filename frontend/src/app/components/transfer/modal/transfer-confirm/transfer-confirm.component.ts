import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Transfer } from '../../transfer.model';


@Component({
  selector: 'app-transfer-confirm',
  templateUrl: './transfer-confirm.component.html',
  styleUrls: ['./transfer-confirm.component.scss']
})
export class TransferConfirmComponent implements OnInit {

  primaryColor: any;
  title: string;
  transfer: Transfer[];
  backButton: string;
  confirmButton: string;
  displayedColumns: string[] = ['contaOrigem', 'contaDestino', 'valor', 'taxa', 'dataAgendamento', 'dataTransferencia'];

  constructor(
    public dialogRef: MatDialogRef<TransferConfirmComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    this.title = data.title ? data.title : '';
    this.transfer = data.transfer ? [data.transfer] : [];
    this.backButton = data.backButton ? data.backButton : 'Voltar';
    this.confirmButton = data.confirmButton ? data.confirmButton : 'Confirmar';
  }

  ngOnInit(): void {
  }

  onClose(): void {
    this.dialogRef.close();
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }

}
