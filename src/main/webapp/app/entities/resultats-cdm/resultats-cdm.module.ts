import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    ResultatsCdmService,
    ResultatsCdmPopupService,
    ResultatsCdmComponent,
    ResultatsCdmDetailComponent,
    ResultatsCdmDialogComponent,
    ResultatsCdmPopupComponent,
    ResultatsCdmDeletePopupComponent,
    ResultatsCdmDeleteDialogComponent,
    resultatsRoute,
    resultatsPopupRoute,
    ResultatsCdmResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...resultatsRoute,
    ...resultatsPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ResultatsCdmComponent,
        ResultatsCdmDetailComponent,
        ResultatsCdmDialogComponent,
        ResultatsCdmDeleteDialogComponent,
        ResultatsCdmPopupComponent,
        ResultatsCdmDeletePopupComponent,
    ],
    entryComponents: [
        ResultatsCdmComponent,
        ResultatsCdmDialogComponent,
        ResultatsCdmPopupComponent,
        ResultatsCdmDeleteDialogComponent,
        ResultatsCdmDeletePopupComponent,
    ],
    providers: [
        ResultatsCdmService,
        ResultatsCdmPopupService,
        ResultatsCdmResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmResultatsCdmModule {}
