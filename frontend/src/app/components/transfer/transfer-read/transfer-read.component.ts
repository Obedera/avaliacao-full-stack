import { Component, OnInit } from '@angular/core';
import { Transfer } from '../transfer.model';
import { TransferService } from '../transfer.service';

@Component({
  selector: 'app-transfer-read',
  templateUrl: './transfer-read.component.html',
  styleUrls: ['./transfer-read.component.scss']
})
export class TransferReadComponent implements OnInit {

  constructor(
    private transferService: TransferService
  ) { }

  transfers: Transfer[] = []

  displayedColumns: string[] = ['id', 'contaOrigem', 'contaDestino', 'valor', 'taxa', 'dataAgendamento', 'dataTransferencia'];

  ngOnInit(): void {
    this.transferService.read().subscribe((resp) => {
      this.transfers = resp.historicoTransferencias;
    })
  }
}
