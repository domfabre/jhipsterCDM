/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterCdmTestModule } from '../../../test.module';
import { JoueursCdmDetailComponent } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm-detail.component';
import { JoueursCdmService } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.service';
import { JoueursCdm } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.model';

describe('Component Tests', () => {

    describe('JoueursCdm Management Detail Component', () => {
        let comp: JoueursCdmDetailComponent;
        let fixture: ComponentFixture<JoueursCdmDetailComponent>;
        let service: JoueursCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [JoueursCdmDetailComponent],
                providers: [
                    JoueursCdmService
                ]
            })
            .overrideTemplate(JoueursCdmDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JoueursCdmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JoueursCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new JoueursCdm(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.joueurs).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
