import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCdmSharedModule } from '../../shared';
import {
    MatchsCdmService,
    MatchsCdmPopupService,
    MatchsCdmComponent,
    MatchsCdmDetailComponent,
    MatchsCdmDialogComponent,
    MatchsCdmPopupComponent,
    MatchsCdmDeletePopupComponent,
    MatchsCdmDeleteDialogComponent,
    matchsRoute,
    matchsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...matchsRoute,
    ...matchsPopupRoute,
];

@NgModule({
    imports: [
        JhipsterCdmSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MatchsCdmComponent,
        MatchsCdmDetailComponent,
        MatchsCdmDialogComponent,
        MatchsCdmDeleteDialogComponent,
        MatchsCdmPopupComponent,
        MatchsCdmDeletePopupComponent,
    ],
    entryComponents: [
        MatchsCdmComponent,
        MatchsCdmDialogComponent,
        MatchsCdmPopupComponent,
        MatchsCdmDeleteDialogComponent,
        MatchsCdmDeletePopupComponent,
    ],
    providers: [
        MatchsCdmService,
        MatchsCdmPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmMatchsCdmModule {}
