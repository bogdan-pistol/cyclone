import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class HttpService {
  constructor(private http: HttpClient) {}

  get(url) {
    return this.http.get(url);
  }

  post(url, body) {
    return this.http.post(url, body);
  }

  delete(id) {
    return this.http.delete(id);
  }
}
