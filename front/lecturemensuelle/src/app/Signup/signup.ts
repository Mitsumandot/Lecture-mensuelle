import {Component} from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
    selector:'app-signup',
    standalone:true,
    imports: [FormsModule],
    templateUrl: './signup.html',
    styleUrl: './signup.css'
})

export class Signup {

    user = {
        name: '',
        email: '',
        password: ''
    }

    submitForm(){
        console.log(this.user);
    }

}