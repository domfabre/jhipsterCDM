import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { NationsCdmComponent } from './nations-cdm.component';
import { NationsCdmDetailComponent } from './nations-cdm-detail.component';
import { NationsCdmPopupComponent } from './nations-cdm-dialog.component';
import { NationsCdmDeletePopupComponent } from './nations-cdm-delete-dialog.component';

export const nationsRoute: Routes = [
    {
        path: 'nations-cdm',
        component: NationsCdmComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.nations.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'nations-cdm/:id',
        component: NationsCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.nations.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nationsPopupRoute: Routes = [
    {
        path: 'nations-cdm-new',
        component: NationsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.nations.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'nations-cdm/:id/edit',
        component: NationsCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.nations.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'nations-cdm/:id/delete',
        component: NationsCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.nations.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
