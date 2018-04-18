import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterCdmNationsCdmModule } from './nations-cdm/nations-cdm.module';
import { JhipsterCdmMatchsCdmModule } from './matchs-cdm/matchs-cdm.module';
import { JhipsterCdmResultatsCdmModule } from './resultats-cdm/resultats-cdm.module';
import { JhipsterCdmJoueursCdmModule } from './joueurs-cdm/joueurs-cdm.module';
import { JhipsterCdmParisCdmModule } from './paris-cdm/paris-cdm.module';
import { JhipsterCdmStadesCdmModule } from './stades-cdm/stades-cdm.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterCdmNationsCdmModule,
        JhipsterCdmMatchsCdmModule,
        JhipsterCdmResultatsCdmModule,
        JhipsterCdmJoueursCdmModule,
        JhipsterCdmParisCdmModule,
        JhipsterCdmStadesCdmModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmEntityModule {}
