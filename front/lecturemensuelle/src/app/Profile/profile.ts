import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService } from "../core/services/user.service";
import { OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { RouterLink } from "@angular/router";
import { UserBookService } from "../core/services/userbook.service";
import { UserBook } from "../core/userbook.model";
import { Header } from "../Header/header";
import { BookService } from "../core/services/book.service";
import { Book } from "../core/book.model";
@Component({
    selector:'app-profile',
    standalone:true,
    imports:[FormsModule, Header, RouterLink],
    templateUrl:'./profile.html',
    styleUrl:'./profile.css'
})

export class Profile implements OnInit {

    constructor(private userService : UserService, private router: Router, 
        private userBookService : UserBookService, private bookService: BookService){}

    user = {
        username: '',
        email: ''
    }

    userBooks : UserBook[] = [];
    filteredBooks : Book[] = [];
    searchQuery: string = '';
    isSearching: boolean = false;
    showUserBooks: boolean = true;

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

    onSearch(query: string): void {
        this.searchQuery = query;
        if (!this.searchQuery.trim()) {
            this.showUserBooks = true;
            this.isSearching = false;
            
        } else {
           
            this.isSearching = true;
            this.showUserBooks = false;
            this.bookService.searchBooks(this.searchQuery).subscribe({
                next: (response: Book[]) => {
                    this.filteredBooks = response;
                    this.isSearching = false;
                },
                error: (err) => {
                    console.error('Search error:', err);
                    this.isSearching = false;
                    this.filteredBooks = [];
                }
            });
        }
    }
    
    deleteUserBook(id : number): void {
        this.userBookService.DeleteUserBook(id).subscribe({
            next: (response: UserBook) => {
                this.ngOnInit();
                console.log(response);
            },
            error: (err) => {
                console.log(err);
            }
        })
    }

    
}