import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import { AlpacaClientService } from 'src/app/services/alpaca-client.service';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-key',
  templateUrl: './key.component.html',
  styleUrls: ['./key.component.scss']
})
export class KeyComponent implements OnInit {

  keyApi = new FormControl('', [Validators.required]);
  secretKey = new FormControl('', [Validators.required]);
  baseUri = new FormControl('', [Validators.required]);

  account: Account;
  
  constructor(private alpacaClientService: AlpacaClientService) { }

  ngOnInit() {
  }

  setAccountInfo(): void {
    console.log('in setAccountInfo' + this.baseUri.value);
    this.alpacaClientService.setConfig(this.keyApi.value, this.secretKey.value, this.baseUri.value);

    this.alpacaClientService.getAccount().subscribe(
      (data) => {
        this.account = data;
      }
    );
  }

  logOut(): void {
    this.account = null;
  }
}
