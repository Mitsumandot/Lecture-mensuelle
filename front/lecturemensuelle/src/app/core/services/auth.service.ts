
import { HttpClient } from "@angular/common/http";
import { User } from "../user.model";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { JwtService } from "./jwt.service";
import { tap } from "rxjs";


@Injectable({providedIn: 'root'})
export class AuthService {
    private api = "http://localhost:8080/auth";

    constructor(private http: HttpClient, private jwtService: JwtService){}

    signup(credential: {
        username: string,
        email: string,
        password: string
    }): Observable<any> {
        return this.http.post(`${this.api}/signup`, credential);
    }

    verify(verification: {
        email: string,
        verificationCode: string
    }): Observable<any> {
        return this.http.post(`${this.api}/verify`, verification);
    }

    login(credential: {
        email: string,
        password: string
    }): Observable<User> {
        return this.http.post<User>(`${this.api}/login`, credential)
        .pipe(tap((response) => {
            this.setAuth(response)}));
    }


    setAuth(user: User): void {
        this.jwtService.saveToken(user.jwtToken);
    }

}