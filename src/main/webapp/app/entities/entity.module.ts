import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterCdmRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { JhipsterCdmCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { JhipsterCdmLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { JhipsterCdmDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { JhipsterCdmTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { JhipsterCdmEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { JhipsterCdmJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { JhipsterCdmJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterCdmRegionMySuffixModule,
        JhipsterCdmCountryMySuffixModule,
        JhipsterCdmLocationMySuffixModule,
        JhipsterCdmDepartmentMySuffixModule,
        JhipsterCdmTaskMySuffixModule,
        JhipsterCdmEmployeeMySuffixModule,
        JhipsterCdmJobMySuffixModule,
        JhipsterCdmJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCdmEntityModule {}
