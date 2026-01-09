import { Routes } from '@angular/router';
import { Home } from './Home/home';
import { Signup } from './Signup/signup';
import { Login } from './Login/login';
import { Profile } from './Profile/profile';
import { BookPage } from './Book/book';

export const routes: Routes = [
    { path : '', component: Home},
    { path: 'signup', component: Signup},
    { path: 'login', component: Login},
    { path: 'profile', component: Profile},
    { path: 'book/:id', component: BookPage}
];
