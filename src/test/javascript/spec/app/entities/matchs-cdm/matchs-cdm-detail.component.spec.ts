/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { MatchsCdmDetailComponent } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm-detail.component';
import { MatchsCdmService } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm.service';
import { MatchsCdm } from '../../../../../../main/webapp/app/entities/matchs-cdm/matchs-cdm.model';

describe('Component Tests', () => {

    describe('MatchsCdm Management Detail Component', () => {
        let comp: MatchsCdmDetailComponent;
        let fixture: ComponentFixture<MatchsCdmDetailComponent>;
        let service: MatchsCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [MatchsCdmDetailComponent],
                providers: [
                    MatchsCdmService
                ]
            })
            .overrideTemplate(MatchsCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MatchsCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MatchsCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MatchsCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.matchs).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
