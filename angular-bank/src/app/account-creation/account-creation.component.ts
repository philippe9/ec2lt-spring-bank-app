import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { DataProviderService } from '../data-provider.service';

@Component({
  selector: 'app-account-creation',
  imports: [
    FormsModule,
    ButtonModule,
    InputTextModule,
    CardModule,
    MessageModule,
    ToastModule,
  ],
  providers: [MessageService],
  templateUrl: './account-creation.component.html',
  styleUrl: './account-creation.component.scss',
})
export class AccountCreationComponent {
  email: string = '';
  password: string = '';
  fullName: string = '';
  constructor(
    private readonly dataProvider: DataProviderService,
    private messageService: MessageService,
    private router: Router
  ) {}
  onLogin() {
    console.log('Login attempted with', this.email, this.password);
    // Add authentication logic here
    if (this.email.length > 5 && this.password.length > 3) {
      this.dataProvider
        .createAccount({
          email: this.email,
          password: this.password,
          fullName: this.fullName,
          role:'USER'
        })
        .subscribe({
          next: (data: any) => {
            if (data) {
              this.router.navigate(['/login']);
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
}
