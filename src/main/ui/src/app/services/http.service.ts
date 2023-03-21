import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class HttpService {
	baseUrl: string = 'http://localhost:9000/api';

	constructor(private client: HttpClient) { }

	get(endpoint: string) {
		return this.client.get(this.baseUrl + endpoint, { headers: this.setHeaders() });
	}

	post(endpoint: string, body: any) {
		return this.client.post(this.baseUrl + endpoint, body, { headers: this.setHeaders() });
	}

	put(endpoint: string, body: any) {
		return this.client.put(this.baseUrl + endpoint, body, { headers: this.setHeaders() });
	}

	patch(endpoint: string, body: any) {
		return this.client.patch(this.baseUrl + endpoint, body, { headers: this.setHeaders() });
	}

	delete(endpoint: string) {
		return this.client.delete(this.baseUrl + endpoint, { headers: this.setHeaders() });
	}

	setHeaders() {
		return new HttpHeaders({
			'Content-Type': 'application/json',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, PATCH, DELETE',
			'Accept': 'application/json',
		});
	}
}
