import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { DataProviderService } from '../data-provider.service';
import { DialogModule } from 'primeng/dialog';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SelectModule } from 'primeng/select';
@Component({
  selector: 'app-transactions',
  imports: [
    CommonModule,
    FormsModule,
    ButtonModule,
    InputTextModule,
    CardModule,
    MessageModule,
    ToastModule,
    TableModule,
    DialogModule,
    SelectModule,
  ],
  providers: [MessageService],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss',
})
export class TransactionsComponent implements OnInit {
  transactions = [];
  operations = [
    { name: 'Depot', code: 'depot' },
    { name: 'Retrait', code: 'retrait' },
    { name: 'Transfert', code: 'transfert' },
  ];
  visible: boolean = false;
  visibleTransaction: boolean = false;
  user = JSON.parse(localStorage.getItem('user') ?? '');
  account: any;
  type = { name: 'Depot', code: 'depot' };
  typeAccount = '';
  montant: string = '';
  destination: string = '';
  // transactions = [];
  constructor(
    private readonly dataProvider: DataProviderService,
    private messageService: MessageService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.dataProvider.getBankAccount(this.user.id).subscribe({
      next: (data: any) => {
        this.account = data;
        this.dataProvider
          .getAccountTransactions(data.id)
          .subscribe((res: any) => {
            console.log(res);
            if (res) {
              this.transactions = res;
            }
          });
      },
      error: (err) => {},
    });
  }
  showDialog() {
    this.visible = true;
  }
  bankAccountCreation() {
    if (this.typeAccount.length > 2 && this.montant.length > 2) {
      this.dataProvider
        .createBankAccount({
          type: this.typeAccount,
          montant: this.montant,
          idUtilisateur: this.user.id,
          utilisateur: this.user,
        })
        .subscribe({
          next: (data: any) => {
            if (data) {
              location.reload();
            }
          },
          error: (err) => {},
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Inserez des donnees correctes',
      });
    }
  }
  createTransaction() {
    if (this.montant.length > 2) {
      this.dataProvider
        .createTransaction({
          type: this.type.code,
          montant: parseInt(this.montant),
          idUtilisateur: this.user.id,
          destination:
            this.type.code === 'transfert'
              ? parseInt(this.destination)
              : this.account.id,
          compte: this.account,
          idCompte: this.account.id,
        })
        .subscribe({
          next: (data: any) => {
            if (data) {
              location.reload();
            }
          },
          error: (err) => {},
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Inserez des donnees correctes',
      });
    }
  }
  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
