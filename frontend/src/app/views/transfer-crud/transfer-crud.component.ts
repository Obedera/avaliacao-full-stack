import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transfer-crud',
  templateUrl: './transfer-crud.component.html',
  styleUrls: ['./transfer-crud.component.scss']
})
export class TransferCrudComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  navigateToTransferCreate(): void {
    this.router.navigate(['/transfer/register'])
  }

  navigateToTransferHistory(): void {
    this.router.navigate([''])
  }

}
