import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MatchsCdm } from './matchs-cdm.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MatchsCdm>;

@Injectable()
export class MatchsCdmService {

    private resourceUrl =  SERVER_API_URL + 'api/matchs';

    constructor(private http: HttpClient) { }

    create(matchs: MatchsCdm): Observable<EntityResponseType> {
        const copy = this.convert(matchs);
        return this.http.post<MatchsCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(matchs: MatchsCdm): Observable<EntityResponseType> {
        const copy = this.convert(matchs);
        return this.http.put<MatchsCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MatchsCdm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MatchsCdm[]>> {
        const options = createRequestOption(req);
        return this.http.get<MatchsCdm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MatchsCdm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MatchsCdm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MatchsCdm[]>): HttpResponse<MatchsCdm[]> {
        const jsonResponse: MatchsCdm[] = res.body;
        const body: MatchsCdm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MatchsCdm.
     */
    private convertItemFromServer(matchs: MatchsCdm): MatchsCdm {
        const copy: MatchsCdm = Object.assign({}, matchs);
        return copy;
    }

    /**
     * Convert a MatchsCdm to a JSON which can be sent to the server.
     */
    private convert(matchs: MatchsCdm): MatchsCdm {
        const copy: MatchsCdm = Object.assign({}, matchs);
        return copy;
    }
}
