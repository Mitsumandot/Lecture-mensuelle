import {Component} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
    selector:'app-signup',
    standalone:true,
    imports: [FormsModule],
    templateUrl: './signup.html',
    styleUrl: './signup.css'
})



export class Signup {

    constructor(private auth: AuthService, private router: Router){}

    user = {
        username: '',
        email: '',
        password: ''
    }

    verificationRequest = {
        email: '',
        verificationCode: '0'
    }

    

    visibility: string = "hidden";

    submitSignup(){
        
        this.auth.signup(this.user).subscribe({
            next: (response) => {
                console.log('Signup Successful !', response);
                this.visibility = "visible";
                this.verificationRequest.email = this.user.email;
            },
            error: (err) => {
                console.log('Signup failed', err);
                this.visibility = "hidden";
            }
        });
    }

    submitVerification(){
        this.auth.verify(this.verificationRequest).subscribe({
            next: (response) => {
                console.log('Verification successful !', response);
                this.router.navigate(['/login']);
            },
            error: (err) => {
                console.log('Verification failed', err);
            }
        });
    }

}