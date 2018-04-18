/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ParisCdmDetailComponent } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm-detail.component';
import { ParisCdmService } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.service';
import { ParisCdm } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.model';

describe('Component Tests', () => {

    describe('ParisCdm Management Detail Component', () => {
        let comp: ParisCdmDetailComponent;
        let fixture: ComponentFixture<ParisCdmDetailComponent>;
        let service: ParisCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ParisCdmDetailComponent],
                providers: [
                    ParisCdmService
                ]
            })
            .overrideTemplate(ParisCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ParisCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParisCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ParisCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.paris).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
