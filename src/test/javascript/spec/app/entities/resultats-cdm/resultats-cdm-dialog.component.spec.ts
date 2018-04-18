/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ResultatsCdmDialogComponent } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm-dialog.component';
import { ResultatsCdmService } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.service';
import { ResultatsCdm } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.model';
import { NationsCdmService } from '../../../../../../main/webapp/app/entities/nations-cdm';
import { MatchsCdmService } from '../../../../../../main/webapp/app/entities/matchs-cdm';

describe('Component Tests', () => {

    describe('ResultatsCdm Management Dialog Component', () => {
        let comp: ResultatsCdmDialogComponent;
        let fixture: ComponentFixture<ResultatsCdmDialogComponent>;
        let service: ResultatsCdmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ResultatsCdmDialogComponent],
                providers: [
                    NationsCdmService,
                    MatchsCdmService,
                    ResultatsCdmService
                ]
            })
            .overrideTemplate(ResultatsCdmDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResultatsCdmDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResultatsCdmService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ResultatsCdm(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.resultats = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'resultatsListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ResultatsCdm();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.resultats = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'resultatsListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
