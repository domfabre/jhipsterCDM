/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterCdmTestModule } from '../../../test.module';
import { JoueursCdmComponent } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.component';
import { JoueursCdmService } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.service';
import { JoueursCdm } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.model';

describe('Component Tests', () => {

    describe('JoueursCdm Management Component', () => {
        let comp: JoueursCdmComponent;
        let fixture: ComponentFixture<JoueursCdmComponent>;
        let service: JoueursCdmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [JoueursCdmComponent],
                providers: [
                    JoueursCdmService
                ]
            })
            .overrideTemplate(JoueursCdmComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JoueursCdmComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JoueursCdmService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new JoueursCdm(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.joueurs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
