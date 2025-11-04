import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AuthService } from "../core/services/auth.service";
import { JwtService } from "../core/services/jwt.service";
import { Router } from "@angular/router";

@Component({
    selector:'app-login',
    standalone:true,
    imports:[FormsModule],
    templateUrl:'./login.html',
    styleUrl:'./login.css'
})


export class Login {

    constructor(private auth: AuthService, private jwtService: JwtService, private router: Router){}


    user = {
        email: '',
        password: ''
    }

    loginError: string = "hidden";
    loginErrorContent: string = "";

    submitLogin(){
        let myObservable = this.auth.login(this.user).subscribe({
            next: (response) => {
                console.log("JWT Token", this.jwtService.getToken());
                this.router.navigate(["/profile"]);
            },
            error: (err) => {
                if(err.error.text == "Bad credentials"){
                    this.loginError = "visible";
                    this.loginErrorContent = "Wrong Password :(";
                }
                else if(err.error.text == "User not found"){
                    this.loginError = "visible";
                    this.loginErrorContent = "Email not found :(";
                }
                console.log("EROOR", err);
            }
        });
    }

    

    

}