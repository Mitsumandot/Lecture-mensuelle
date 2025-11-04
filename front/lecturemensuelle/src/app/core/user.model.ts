export interface User {
    jwtToken: string;
    expireIn: number;
    username: string;
}