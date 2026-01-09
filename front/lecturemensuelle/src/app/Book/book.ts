import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService } from "../core/services/user.service";
import { OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserBookService } from "../core/services/userbook.service";
import { UserBook } from "../core/userbook.model";
import { Header } from "../Header/header";
import { BookService } from "../core/services/book.service";
import { Book } from "../core/book.model";
import { ActivatedRoute } from "@angular/router";
@Component({
    selector:'book',
    standalone:true,
    imports:[FormsModule, Header],
    templateUrl:'./book.html',
    styleUrl:'./book.css'
})

export class BookPage {
    book: Book | null = null;
    isLoading: boolean = true;
    error: string | null = null;
    bookId: number = 500000;
    userBook: UserBook = {
        id : 0,
        bookName: '',
        bookImage: '',
        author: '',
        username: '',
        review: '',
        rating: 0,
        reviewTitle: '',
        status: '',
        favourite: false

    }

    constructor(private route: ActivatedRoute, private bookService: BookService, private userBookService: UserBookService){}

    ngOnInit() {
        const potentialId = this.route.snapshot.paramMap.get('id');
        if(potentialId){
            const id = Number(potentialId);
            this.bookService.getBookInfo(id).subscribe({
                next: (response : Book) => {
                    this.book = response;
                    this.isLoading = false;
                    console.log(this.book);
                    this.bookId = this.book.id;
                },
                error: (err) => {
                    console.error('Error loading book:', err);
                    this.error = 'Erreur lors du chargement du livre';
                    this.isLoading = false;
                }
            })
        }
        else {
            this.error = 'ID du livre non trouvé';
            this.isLoading = false;
        }
    }

    submitRating() {
        console.log(this.userBook);
        this.userBookService.CreateUserBook(this.bookId, this.userBook).subscribe({
            next: (response) => {
                console.log(response);
            },
            error: (err) => {
                console.log(err);
            }
        });
    }




}
