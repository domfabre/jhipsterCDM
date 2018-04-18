import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { NationsCdm } from './nations-cdm.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<NationsCdm>;

@Injectable()
export class NationsCdmService {

    private resourceUrl =  SERVER_API_URL + 'api/nations';

    constructor(private http: HttpClient) { }

    create(nations: NationsCdm): Observable<EntityResponseType> {
        const copy = this.convert(nations);
        return this.http.post<NationsCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(nations: NationsCdm): Observable<EntityResponseType> {
        const copy = this.convert(nations);
        return this.http.put<NationsCdm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<NationsCdm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<NationsCdm[]>> {
        const options = createRequestOption(req);
        return this.http.get<NationsCdm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<NationsCdm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: NationsCdm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<NationsCdm[]>): HttpResponse<NationsCdm[]> {
        const jsonResponse: NationsCdm[] = res.body;
        const body: NationsCdm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to NationsCdm.
     */
    private convertItemFromServer(nations: NationsCdm): NationsCdm {
        const copy: NationsCdm = Object.assign({}, nations);
        return copy;
    }

    /**
     * Convert a NationsCdm to a JSON which can be sent to the server.
     */
    private convert(nations: NationsCdm): NationsCdm {
        const copy: NationsCdm = Object.assign({}, nations);
        return copy;
    }
}
