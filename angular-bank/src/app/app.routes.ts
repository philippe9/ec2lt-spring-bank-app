import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccountCreationComponent } from './account-creation/account-creation.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { authGuardGuard } from './auth-guard.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'account-creation', component: AccountCreationComponent },
  {
    path: 'transactions',
    canActivate: [authGuardGuard],
    component: TransactionsComponent,
  },
  { path: 'not-found', component: NotFoundComponent },
  {
    path: '**',
    redirectTo: 'not-found',
  },
];
