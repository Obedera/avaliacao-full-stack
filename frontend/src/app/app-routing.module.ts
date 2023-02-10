import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransferCreateComponent } from './components/transfer/transfer-create/transfer-create.component';
import { TransferReadComponent } from './components/transfer/transfer-read/transfer-read.component';


const routes: Routes = [
  {
    path: "",
    component: TransferReadComponent
  },
  {
    path: "transfer/register",
    component: TransferCreateComponent
  },
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
