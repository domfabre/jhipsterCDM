import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StadesCdmComponent } from './stades-cdm.component';
import { StadesCdmDetailComponent } from './stades-cdm-detail.component';
import { StadesCdmPopupComponent } from './stades-cdm-dialog.component';
import { StadesCdmDeletePopupComponent } from './stades-cdm-delete-dialog.component';

export const stadesRoute: Routes = [
    {
        path: 'stades-cdm',
        component: StadesCdmComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.stades.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stades-cdm/:id',
        component: StadesCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.stades.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stadesPopupRoute: Routes = [
    {
        path: 'stades-cdm-new',
        component: StadesCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.stades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stades-cdm/:id/edit',
        component: StadesCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.stades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stades-cdm/:id/delete',
        component: StadesCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.stades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
