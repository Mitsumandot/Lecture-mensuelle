import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService } from "../core/services/user.service";
import { OnInit } from "@angular/core";
@Component({
    selector:'app-profile',
    standalone:true,
    imports:[FormsModule],
    templateUrl:'./profile.html',
    styleUrl:'./profile.css'
})

export class Profile implements OnInit {

    constructor(private userService : UserService){}

    ngOnInit(): void {
        let myObservable = this.userService.getUser().subscribe({
            next: (response) => {
                console.log(response);
            },
            error: (err) => {
                console.log(err);
            }
        })
    }



    
}