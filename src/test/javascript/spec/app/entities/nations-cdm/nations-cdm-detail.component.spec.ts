/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { NationsCdmDetailComponent } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm-detail.component';
import { NationsCdmService } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm.service';
import { NationsCdm } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm.model';

describe('Component Tests', () => {

    describe('NationsCdm Management Detail Component', () => {
        let comp: NationsCdmDetailComponent;
        let fixture: ComponentFixture<NationsCdmDetailComponent>;
        let service: NationsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [NationsCdmDetailComponent],
                providers: [
                    NationsCdmService
                ]
            })
            .overrideTemplate(NationsCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NationsCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NationsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new NationsCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.nations).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
