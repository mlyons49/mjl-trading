import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import { AlpacaClientService } from 'src/app/services/alpaca-client.service';

@Component({
  selector: 'app-key',
  templateUrl: './key.component.html',
  styleUrls: ['./key.component.css']
})
export class KeyComponent implements OnInit {

  keyApi = '';
  secretKey = '';
  baseUri = '';

  account: Account;
  
  constructor(private alpacaClientService: AlpacaClientService) { }

  ngOnInit() {
  }

  setAccountInfo(): void {
    console.log('in setAccountInfo');
    this.alpacaClientService.setConfig(this.keyApi, this.secretKey, this.baseUri);

    this.alpacaClientService.getAccount().subscribe(
      (data) => {
        this.account = data;
        console.log('this.account:', this.account);
      }
    );
  }

  logOut(): void {
    console.log('in logout');
    this.account = null;
  }
}
