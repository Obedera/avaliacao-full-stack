import { NgModule, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



// Views 
import { TransferCrudComponent } from './views/transfer-crud/transfer-crud.component';


// Components
import { TransferReadComponent } from './components/transfer/transfer-read/transfer-read.component';


// Directives
import { CurrencyMaskDirective } from './directives/currency-mask.directive';


// Material
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';






// Set Locale PT-BR 
import localePt from '@angular/common/locales/pt'
import { registerLocaleData } from '@angular/common';
import { TransferCreateComponent } from './components/transfer/transfer-create/transfer-create.component';
import { TransferConfirmComponent } from './components/transfer/modal/transfer-confirm/transfer-confirm.component';

registerLocaleData(localePt)

@NgModule({
  declarations: [
    AppComponent,
    TransferCrudComponent,
    TransferReadComponent,
    TransferCreateComponent,
    CurrencyMaskDirective,
    TransferConfirmComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatDialogModule
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
