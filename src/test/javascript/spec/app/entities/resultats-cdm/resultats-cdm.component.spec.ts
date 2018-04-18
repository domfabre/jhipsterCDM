/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ResultatsCdmComponent } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.component';
import { ResultatsCdmService } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.service';
import { ResultatsCdm } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.model';

describe('Component Tests', () => {

    describe('ResultatsCdm Management Component', () => {
        let comp: ResultatsCdmComponent;
        let fixture: ComponentFixture<ResultatsCdmComponent>;
        let service: ResultatsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ResultatsCdmComponent],
                providers: [
                    ResultatsCdmService
                ]
            })
            .overrideTemplate(ResultatsCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResultatsCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResultatsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ResultatsCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.resultats[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
