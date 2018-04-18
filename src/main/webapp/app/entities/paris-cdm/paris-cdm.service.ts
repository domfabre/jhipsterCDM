import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ParisCdm } from './paris-cdm.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ParisCdm>;

@Injectable()
export class ParisCdmService {

    private resourceUrl =  SERVER_API_URL + 'api/parises';

    constructor(private http: HttpClient) { }

    create(paris: ParisCdm): Observable<EntityResponseType> {
        const copy = this.convert(paris);
        return this.http.post<ParisCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(paris: ParisCdm): Observable<EntityResponseType> {
        const copy = this.convert(paris);
        return this.http.put<ParisCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ParisCdm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ParisCdm[]>> {
        const options = createRequestOption(req);
        return this.http.get<ParisCdm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ParisCdm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ParisCdm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ParisCdm[]>): HttpResponse<ParisCdm[]> {
        const jsonResponse: ParisCdm[] = res.body;
        const body: ParisCdm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ParisCdm.
     */
    private convertItemFromServer(paris: ParisCdm): ParisCdm {
        const copy: ParisCdm = Object.assign({}, paris);
        return copy;
    }

    /**
     * Convert a ParisCdm to a JSON which can be sent to the server.
     */
    private convert(paris: ParisCdm): ParisCdm {
        const copy: ParisCdm = Object.assign({}, paris);
        return copy;
    }
}
