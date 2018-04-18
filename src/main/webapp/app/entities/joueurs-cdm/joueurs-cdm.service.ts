import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JoueursCdm } from './joueurs-cdm.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<JoueursCdm>;

@Injectable()
export class JoueursCdmService {

    private resourceUrl =  SERVER_API_URL + 'api/joueurs';

    constructor(private http: HttpClient) { }

    create(joueurs: JoueursCdm): Observable<EntityResponseType> {
        const copy = this.convert(joueurs);
        return this.http.post<JoueursCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(joueurs: JoueursCdm): Observable<EntityResponseType> {
        const copy = this.convert(joueurs);
        return this.http.put<JoueursCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<JoueursCdm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<JoueursCdm[]>> {
        const options = createRequestOption(req);
        return this.http.get<JoueursCdm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<JoueursCdm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: JoueursCdm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<JoueursCdm[]>): HttpResponse<JoueursCdm[]> {
        const jsonResponse: JoueursCdm[] = res.body;
        const body: JoueursCdm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to JoueursCdm.
     */
    private convertItemFromServer(joueurs: JoueursCdm): JoueursCdm {
        const copy: JoueursCdm = Object.assign({}, joueurs);
        return copy;
    }

    /**
     * Convert a JoueursCdm to a JSON which can be sent to the server.
     */
    private convert(joueurs: JoueursCdm): JoueursCdm {
        const copy: JoueursCdm = Object.assign({}, joueurs);
        return copy;
    }
}
