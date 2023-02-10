import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TransferCrudComponent } from './views/transferencia-crud/transfer-crud.component';

const routes: Routes = [
  {
    path: "",
    component: TransferCrudComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
