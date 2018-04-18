/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { NationsCdmComponent } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm.component';
import { NationsCdmService } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm.service';
import { NationsCdm } from '../../../../../../main/webapp/app/entities/nations-cdm/nations-cdm.model';

describe('Component Tests', () => {

    describe('NationsCdm Management Component', () => {
        let comp: NationsCdmComponent;
        let fixture: ComponentFixture<NationsCdmComponent>;
        let service: NationsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [NationsCdmComponent],
                providers: [
                    NationsCdmService
                ]
            })
            .overrideTemplate(NationsCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NationsCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NationsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new NationsCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.nations[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
