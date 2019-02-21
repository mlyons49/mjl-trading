import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlpacaClientService {

  private keyApi: string;
  private secretKey: string;
  private baseUri: string;

  constructor(private httpClient: HttpClient) { }

  public setConfig(keyApi:string, secretKey:string, baseUri:string) {
    this.keyApi = keyApi;
    this.secretKey = secretKey;
    this.baseUri = baseUri;
  }

  public getAccount(): Observable<Account> {
    const uri = '/v1/account';
    return this.getWithKeys(uri);
  }

  private getWithKeys<T>(uri: string) : Observable<T> {
    return this.httpClient.get<T>( 
      this.buildUrl(uri), 
      {headers: {'APCA-API-KEY-ID' : this.keyApi, 
                 'APCA-API-SECRET-KEY' : this.secretKey}, 
        responseType: 'json'} );
  }

  private postWithKeys<T>(uri: string, jsonBody: object): Observable<T> {
    return this.httpClient.post<T>(
      this.buildUrl(uri), 
      jsonBody,
      {headers: {'APCA-API-KEY-ID' : this.keyApi, 'APCA-API-SECRET-KEY' : this.secretKey}, responseType: 'json'}
    )
  }

  private buildUrl(uri: string):string {
    return encodeURI(this.baseUri + uri);
  }
}
