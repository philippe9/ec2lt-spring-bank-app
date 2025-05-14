import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { DataProviderService } from '../data-provider.service';
import { Route, Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    ButtonModule,
    InputTextModule,
    CardModule,
    MessageModule,
    ToastModule,
  ],
  providers: [MessageService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  constructor(
    private readonly dataProvider: DataProviderService,
    private messageService: MessageService,
    private router: Router
  ) {}
  onLogin() {
    console.log('Login attempted with', this.email, this.password);
    // Add authentication logic here
    if (this.email.length > 5 && this.password.length > 3) {
      this.dataProvider.login(this.email, this.password).subscribe({
        next: (data: any) => {
          if (data) {
            localStorage.setItem('token', data.token);
            localStorage.setItem('user', JSON.stringify(data.utilisateur));
            this.router.navigate(['/transactions']);
          } else {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Credentials incorrecte',
            });
          }
        },
        error: (err) => {
          this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Credentials incorrecte',
            });
        },
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
