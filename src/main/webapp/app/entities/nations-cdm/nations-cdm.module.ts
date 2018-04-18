import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    NationsCdmService,
    NationsCdmPopupService,
    NationsCdmComponent,
    NationsCdmDetailComponent,
    NationsCdmDialogComponent,
    NationsCdmPopupComponent,
    NationsCdmDeletePopupComponent,
    NationsCdmDeleteDialogComponent,
    nationsRoute,
    nationsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...nationsRoute,
    ...nationsPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NationsCdmComponent,
        NationsCdmDetailComponent,
        NationsCdmDialogComponent,
        NationsCdmDeleteDialogComponent,
        NationsCdmPopupComponent,
        NationsCdmDeletePopupComponent,
    ],
    entryComponents: [
        NationsCdmComponent,
        NationsCdmDialogComponent,
        NationsCdmPopupComponent,
        NationsCdmDeleteDialogComponent,
        NationsCdmDeletePopupComponent,
    ],
    providers: [
        NationsCdmService,
        NationsCdmPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmNationsCdmModule {}
