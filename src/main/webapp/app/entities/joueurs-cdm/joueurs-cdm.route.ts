import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JoueursCdmComponent } from './joueurs-cdm.component';
import { JoueursCdmDetailComponent } from './joueurs-cdm-detail.component';
import { JoueursCdmPopupComponent } from './joueurs-cdm-dialog.component';
import { JoueursCdmDeletePopupComponent } from './joueurs-cdm-delete-dialog.component';

export const joueursRoute: Routes = [
    {
        path: 'joueurs-cdm',
        component: JoueursCdmComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.joueurs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'joueurs-cdm/:id',
        component: JoueursCdmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.joueurs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const joueursPopupRoute: Routes = [
    {
        path: 'joueurs-cdm-new',
        component: JoueursCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.joueurs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'joueurs-cdm/:id/edit',
        component: JoueursCdmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.joueurs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'joueurs-cdm/:id/delete',
        component: JoueursCdmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterCdmApp.joueurs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
