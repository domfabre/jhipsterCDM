/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ParisCdmComponent } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.component';
import { ParisCdmService } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.service';
import { ParisCdm } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.model';

describe('Component Tests', () => {

    describe('ParisCdm Management Component', () => {
        let comp: ParisCdmComponent;
        let fixture: ComponentFixture<ParisCdmComponent>;
        let service: ParisCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ParisCdmComponent],
                providers: [
                    ParisCdmService
                ]
            })
            .overrideTemplate(ParisCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ParisCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParisCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ParisCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.parises[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
