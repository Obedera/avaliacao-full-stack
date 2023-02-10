export interface Transfer {
  id?: number,
  contaOrigem: string,
  contaDestino: string,
  valor: number,
  taxa?: number,
  dataAgendamento: string,
  dataTransferencia: string,
}


export interface HistoryTransfer {
  historicoTransferencias: Transfer[]
}