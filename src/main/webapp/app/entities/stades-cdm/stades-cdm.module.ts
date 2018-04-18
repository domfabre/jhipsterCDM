import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    StadesCdmService,
    StadesCdmPopupService,
    StadesCdmComponent,
    StadesCdmDetailComponent,
    StadesCdmDialogComponent,
    StadesCdmPopupComponent,
    StadesCdmDeletePopupComponent,
    StadesCdmDeleteDialogComponent,
    stadesRoute,
    stadesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...stadesRoute,
    ...stadesPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StadesCdmComponent,
        StadesCdmDetailComponent,
        StadesCdmDialogComponent,
        StadesCdmDeleteDialogComponent,
        StadesCdmPopupComponent,
        StadesCdmDeletePopupComponent,
    ],
    entryComponents: [
        StadesCdmComponent,
        StadesCdmDialogComponent,
        StadesCdmPopupComponent,
        StadesCdmDeleteDialogComponent,
        StadesCdmDeletePopupComponent,
    ],
    providers: [
        StadesCdmService,
        StadesCdmPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmStadesCdmModule {}
