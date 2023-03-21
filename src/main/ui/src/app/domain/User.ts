import { Role } from "./Role";

export class User {
	id?: Number;
	username: string = "";
	email: string = "";
	password?: string;
	token?: string;
	role?: Role | Number;

}
