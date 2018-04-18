import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ResultatsCdmComponent } from './resultats-cdm.component';
import { ResultatsCdmDetailComponent } from './resultats-cdm-detail.component';
import { ResultatsCdmPopupComponent } from './resultats-cdm-dialog.component';
import { ResultatsCdmDeletePopupComponent } from './resultats-cdm-delete-dialog.component';

@Injectable()
export class ResultatsCdmResolvePagingParams implements Resolve<any> {

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

export const resultatsRoute: Routes = [
    {
        path: 'resultats-cdm',
        component: ResultatsCdmComponent,
        resolve: {
            'pagingParams': ResultatsCdmResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.resultats.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'resultats-cdm/:id',
        component: ResultatsCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.resultats.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resultatsPopupRoute: Routes = [
    {
        path: 'resultats-cdm-new',
        component: ResultatsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.resultats.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'resultats-cdm/:id/edit',
        component: ResultatsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.resultats.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'resultats-cdm/:id/delete',
        component: ResultatsCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.resultats.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
