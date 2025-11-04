import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class JwtService {


    saveToken(token: string): void{
        window.localStorage["jwtToken"] = token;
    }

    getToken(): string{
        return window.localStorage["jwtToken"];

    }
}