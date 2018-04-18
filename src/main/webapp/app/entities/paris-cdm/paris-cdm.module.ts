import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    ParisCdmService,
    ParisCdmPopupService,
    ParisCdmComponent,
    ParisCdmDetailComponent,
    ParisCdmDialogComponent,
    ParisCdmPopupComponent,
    ParisCdmDeletePopupComponent,
    ParisCdmDeleteDialogComponent,
    parisRoute,
    parisPopupRoute,
    ParisCdmResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...parisRoute,
    ...parisPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ParisCdmComponent,
        ParisCdmDetailComponent,
        ParisCdmDialogComponent,
        ParisCdmDeleteDialogComponent,
        ParisCdmPopupComponent,
        ParisCdmDeletePopupComponent,
    ],
    entryComponents: [
        ParisCdmComponent,
        ParisCdmDialogComponent,
        ParisCdmPopupComponent,
        ParisCdmDeleteDialogComponent,
        ParisCdmDeletePopupComponent,
    ],
    providers: [
        ParisCdmService,
        ParisCdmPopupService,
        ParisCdmResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmParisCdmModule {}
