import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtService } from "./jwt.service";


@Injectable({providedIn: 'root'})
export class BookService {
    private api = "http://localhost:8080/book";

    constructor(private http: HttpClient, private jwtService: JwtService){}

    searchBooks(query: string): Observable<any> {
        const token = this.jwtService.getToken();
        const header = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });
        const requestOption = {headers : header};
        return this.http.get(`${this.api}/search?name=${encodeURIComponent(query)}`, requestOption);
    }

    getBookInfo(id: number): Observable<any> {
        const token = this.jwtService.getToken();
        const header = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })
        const requestOption = {headers: header};
        return this.http.get(`${this.api}/book?id=${encodeURIComponent(id)}`, requestOption);
    }
}