import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MatchsCdmComponent } from './matchs-cdm.component';
import { MatchsCdmDetailComponent } from './matchs-cdm-detail.component';
import { MatchsCdmPopupComponent } from './matchs-cdm-dialog.component';
import { MatchsCdmDeletePopupComponent } from './matchs-cdm-delete-dialog.component';

export const matchsRoute: Routes = [
    {
        path: 'matchs-cdm',
        component: MatchsCdmComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.matchs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'matchs-cdm/:id',
        component: MatchsCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.matchs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const matchsPopupRoute: Routes = [
    {
        path: 'matchs-cdm-new',
        component: MatchsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.matchs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'matchs-cdm/:id/edit',
        component: MatchsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.matchs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'matchs-cdm/:id/delete',
        component: MatchsCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.matchs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
