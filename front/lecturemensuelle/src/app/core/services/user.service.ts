import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtService } from "./jwt.service";



@Injectable({providedIn: 'root'})
export class UserService {
    private api = "http://localhost:8080/users";

    constructor(private http: HttpClient, private jwtService: JwtService){}


    getUser(): Observable<any> {
        const token = this.jwtService.getToken();
        const header = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });
        const requestOption = { headers : header};
        return this.http.get(`${this.api}/me`, requestOption);
    }


}