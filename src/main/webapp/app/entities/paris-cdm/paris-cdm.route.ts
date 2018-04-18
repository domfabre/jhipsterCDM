import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ParisCdmComponent } from './paris-cdm.component';
import { ParisCdmDetailComponent } from './paris-cdm-detail.component';
import { ParisCdmPopupComponent } from './paris-cdm-dialog.component';
import { ParisCdmDeletePopupComponent } from './paris-cdm-delete-dialog.component';

@Injectable()
export class ParisCdmResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const parisRoute: Routes = [
    {
        path: 'paris-cdm',
        component: ParisCdmComponent,
        resolve: {
            'pagingParams': ParisCdmResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.paris.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'paris-cdm/:id',
        component: ParisCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.paris.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const parisPopupRoute: Routes = [
    {
        path: 'paris-cdm-new',
        component: ParisCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.paris.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'paris-cdm/:id/edit',
        component: ParisCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.paris.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'paris-cdm/:id/delete',
        component: ParisCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.paris.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
