import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DataProviderService {
  BASE_URL = 'http://localhost:8080/api/';
  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post(`${this.BASE_URL}auth/login`, { email, password });
  }

  createAccount(user: any) {
    return this.http.post(`${this.BASE_URL}auth/signup`, user);
  }
  createBankAccount(account: any) {
    return this.http.post(`${this.BASE_URL}compte`, account);
  }

  getBankAccount(id: string) {
    return this.http.get(`${this.BASE_URL}compte/user/${id}`);
  }
  getAccountTransactions(id: string) {
    return this.http.get(`${this.BASE_URL}transaction/compte/${id}`);
  }

  createTransaction(transaction: any) {
    return this.http.post(`${this.BASE_URL}transaction`, transaction);
  }
}
