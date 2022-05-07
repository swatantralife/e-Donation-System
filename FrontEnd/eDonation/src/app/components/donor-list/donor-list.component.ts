import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Donation } from 'src/app/common/donation';
import { Donor } from 'src/app/common/donor';
import { DonationService } from 'src/app/services/donation.service';
import { DonorService } from 'src/app/services/donor.service';

@Component({
  selector: 'app-donor-list',
  templateUrl: './donor-list.component.html',
  styleUrls: ['./donor-list.component.css']
})
export class DonorListComponent implements OnInit {

  @Input() donor:Donor = new Donor();
  @Output() changeDonorEvent:EventEmitter<Donor> =new EventEmitter<Donor>(); 
    

  donors!: Donor[];
  donations!: Donation[];
  selectedDonor!: Donor;
  resolvedDonor: Donor = new Donor;
  donorId!: number;

  constructor(private donorService: DonorService,
    private donationService: DonationService) { }

  ngOnInit(): void {
    this.listDonors();
  }

  update() {
    this.changeDonorEvent.emit(this.donor);
  }

  form = new FormGroup({
    donorFormControl: new FormControl('', Validators.required)
  });

  get f(){  
    return this.form.controls;  
  }  
   
  submit(){
    console.log(this.form.value);
    this.listDonations(this.donorId);
  }

  getDonor(id: number){
    this.donorService.getDonor(id).subscribe(
      (data: Donor) => this.resolvedDonor = data
    );
  }

  listDonors(){
    this.donorService.getDonorList().subscribe(
      (data: Donor[]) => this.donors = data
    );
  }

  listDonations(id: number){
    this.donationService.getDonationList(id).subscribe(
      (data: Donation[]) => this.donations = data
    );
  }

  changeDonor(e: Event) {
    
    this.donorId = Number((e.target as HTMLInputElement).value);
    //console.log(Number((e.target as HTMLInputElement).value));
    console.log(this.donorId);
    this.getDonor(this.donorId);
    
  }

}
