import { REMOVE_TOKEN } from './../actions';
import { environment } from '../../environments/environment';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ADD_USER_IMAGE } from '../actions';


@Injectable()
export class ImageService {
    private imageUploadEndpoint: string;
    private token: string;

    constructor(private http: HttpClient,
                private ngRedux: NgRedux<IAppState> ) {
        this.imageUploadEndpoint = `${environment.apiUrl}/image/upload`;
        this.ngRedux.select('token').subscribe((token: any) => {
            this.token = token;
        });
    }

    public uploadImage(file) {
        if (!this.token) {
            return;
        }

        const formData = new FormData();
        console.log(file);
        formData.append("file", file);

        return this
            .http
            .post(this.imageUploadEndpoint, formData, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then((data: any) => {
                this.ngRedux.dispatch({type: ADD_USER_IMAGE, imageId: data.id});
            }, err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            });

    }

    private getAuthenticationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.token}`,
            'Accept': 'application/json'
        });
    }
}
