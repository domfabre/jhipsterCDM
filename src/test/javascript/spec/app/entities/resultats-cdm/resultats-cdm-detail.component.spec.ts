/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ResultatsCdmDetailComponent } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm-detail.component';
import { ResultatsCdmService } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.service';
import { ResultatsCdm } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.model';

describe('Component Tests', () => {

    describe('ResultatsCdm Management Detail Component', () => {
        let comp: ResultatsCdmDetailComponent;
        let fixture: ComponentFixture<ResultatsCdmDetailComponent>;
        let service: ResultatsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ResultatsCdmDetailComponent],
                providers: [
                    ResultatsCdmService
                ]
            })
            .overrideTemplate(ResultatsCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResultatsCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResultatsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ResultatsCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.resultats).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
