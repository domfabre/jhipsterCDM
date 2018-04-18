import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    JoueursCdmService,
    JoueursCdmPopupService,
    JoueursCdmComponent,
    JoueursCdmDetailComponent,
    JoueursCdmDialogComponent,
    JoueursCdmPopupComponent,
    JoueursCdmDeletePopupComponent,
    JoueursCdmDeleteDialogComponent,
    joueursRoute,
    joueursPopupRoute,
} from './';

const ENTITY_STATES = [
    ...joueursRoute,
    ...joueursPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JoueursCdmComponent,
        JoueursCdmDetailComponent,
        JoueursCdmDialogComponent,
        JoueursCdmDeleteDialogComponent,
        JoueursCdmPopupComponent,
        JoueursCdmDeletePopupComponent,
    ],
    entryComponents: [
        JoueursCdmComponent,
        JoueursCdmDialogComponent,
        JoueursCdmPopupComponent,
        JoueursCdmDeleteDialogComponent,
        JoueursCdmDeletePopupComponent,
    ],
    providers: [
        JoueursCdmService,
        JoueursCdmPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmJoueursCdmModule {}
