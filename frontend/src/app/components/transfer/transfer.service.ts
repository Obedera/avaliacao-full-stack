import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HistoryTransfer, Transfer } from './transfer.model';
import { map, catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';


@Injectable({
  providedIn: 'root'
})
export class TransferService {

  constructor(
    private http: HttpClient,
    private snackBar: MatSnackBar
  ) { }

  baseUrl = "http://localhost:8080/api/v1/transferencia"

  showMessage(msg: string): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top"
    })
  }

  read(): Observable<HistoryTransfer>{
    return this.http.get<HistoryTransfer>(`${this.baseUrl}/historico`).pipe(
      map(obj => obj),
      catchError(e => this.errorHandle(e))
    );
  }

  resume(transfer: Transfer): Observable<Transfer>{
    return this.http.post<Transfer>(`${this.baseUrl}/resumo`, transfer).pipe(
      map(obj => obj),
      catchError(e => this.errorHandle(e))
    );
  }

  confirm(transfer: Transfer): Observable<Transfer>{
    return this.http.post<Transfer>(`${this.baseUrl}/confirmar`, transfer).pipe(
      map(obj => obj),
      catchError(e => this.errorHandle(e))
    );
  }

  errorHandle(e: any): Observable<any>{
    this.showMessage(e.error?.message || 'Ocorreu um erro')
    return EMPTY
  }
}
