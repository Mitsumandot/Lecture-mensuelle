import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService } from "../core/services/user.service";
import { OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserBookService } from "../core/services/userbook.service";
import { UserBook } from "../core/userbook.model";
@Component({
    selector:'app-profile',
    standalone:true,
    imports:[FormsModule],
    templateUrl:'./profile.html',
    styleUrl:'./profile.css'
})

export class Profile implements OnInit {

    constructor(private userService : UserService, private router: Router, 
        private userBookService : UserBookService){}

    user = {
        username: '',
        email: ''
    }

    userBooks : UserBook[] = [];

    ngOnInit(): void {
        let myObservable = this.userService.getUser().subscribe({
            next: (response) => {
                this.user.username = response.username;
                this.user.email = response.email;
                console.log(this.user.username);
            },
            error: (err) => {
                this.router.navigate(["/"]);
                console.log(err);
            }
        })
        let myObservable2 = this.userBookService.getCurrentUserBooks().subscribe({
            next: (response: UserBook[]) => {
                this.userBooks = response;
                console.log(this.userBooks[0]);
            }

        })
    }



    
}