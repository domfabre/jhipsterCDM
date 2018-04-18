/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { MatchsCdmComponent } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm.component';
import { MatchsCdmService } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm.service';
import { MatchsCdm } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm.model';

describe('Component Tests', () => {

    describe('MatchsCdm Management Component', () => {
        let comp: MatchsCdmComponent;
        let fixture: ComponentFixture<MatchsCdmComponent>;
        let service: MatchsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [MatchsCdmComponent],
                providers: [
                    MatchsCdmService
                ]
            })
            .overrideTemplate(MatchsCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MatchsCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MatchsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MatchsCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.matchs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
