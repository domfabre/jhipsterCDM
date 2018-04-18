/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ParisCdmDialogComponent } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm-dialog.component';
import { ParisCdmService } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.service';
import { ParisCdm } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.model';
import { JoueursCdmService } from '../../../../../../main/webapp/app/entities/joueurs-cdm';
import { ResultatsCdmService } from '../../../../../../main/webapp/app/entities/resultats-cdm';

describe('Component Tests', () => {

    describe('ParisCdm Management Dialog Component', () => {
        let comp: ParisCdmDialogComponent;
        let fixture: ComponentFixture<ParisCdmDialogComponent>;
        let service: ParisCdmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ParisCdmDialogComponent],
                providers: [
                    JoueursCdmService,
                    ResultatsCdmService,
                    ParisCdmService
                ]
            })
            .overrideTemplate(ParisCdmDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ParisCdmDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParisCdmService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ParisCdm(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.paris = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'parisListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ParisCdm();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.paris = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'parisListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
