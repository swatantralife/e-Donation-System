import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DonorListComponent } from './components/donor-list/donor-list.component';
import { HttpClientModule } from '@angular/common/http';
import { DonorService } from './services/donor.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DonationService } from './services/donation.service';

@NgModule({
  declarations: [
    AppComponent,
    DonorListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [DonorService,DonationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
