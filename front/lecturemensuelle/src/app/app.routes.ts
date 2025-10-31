import { Routes } from '@angular/router';
import { Home } from './Home/home';
import { Signup } from './Signup/signup';

export const routes: Routes = [
    { path : '', component: Home},
    { path: 'signup', component: Signup}
];
