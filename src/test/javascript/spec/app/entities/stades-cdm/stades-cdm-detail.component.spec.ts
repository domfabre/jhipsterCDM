/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { StadesCdmDetailComponent } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm-detail.component';
import { StadesCdmService } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm.service';
import { StadesCdm } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm.model';

describe('Component Tests', () => {

    describe('StadesCdm Management Detail Component', () => {
        let comp: StadesCdmDetailComponent;
        let fixture: ComponentFixture<StadesCdmDetailComponent>;
        let service: StadesCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [StadesCdmDetailComponent],
                providers: [
                    StadesCdmService
                ]
            })
            .overrideTemplate(StadesCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StadesCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StadesCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StadesCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.stades).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
