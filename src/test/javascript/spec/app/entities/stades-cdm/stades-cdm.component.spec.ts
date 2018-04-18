/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { StadesCdmComponent } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm.component';
import { StadesCdmService } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm.service';
import { StadesCdm } from '../../../../../../main/webapp/app/entities/stades-cdm/stades-cdm.model';

describe('Component Tests', () => {

    describe('StadesCdm Management Component', () => {
        let comp: StadesCdmComponent;
        let fixture: ComponentFixture<StadesCdmComponent>;
        let service: StadesCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [StadesCdmComponent],
                providers: [
                    StadesCdmService
                ]
            })
            .overrideTemplate(StadesCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StadesCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StadesCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StadesCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.stades[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
