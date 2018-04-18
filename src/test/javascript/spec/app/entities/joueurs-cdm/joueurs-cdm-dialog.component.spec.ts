/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterCdmTestModule } from '../../../test.module';
import { JoueursCdmDialogComponent } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm-dialog.component';
import { JoueursCdmService } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.service';
import { JoueursCdm } from '../../../../../../main/webapp/app/entities/joueurs-cdm/joueurs-cdm.model';

describe('Component Tests', () => {

    describe('JoueursCdm Management Dialog Component', () => {
        let comp: JoueursCdmDialogComponent;
        let fixture: ComponentFixture<JoueursCdmDialogComponent>;
        let service: JoueursCdmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [JoueursCdmDialogComponent],
                providers: [
                    JoueursCdmService
                ]
            })
            .overrideTemplate(JoueursCdmDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JoueursCdmDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JoueursCdmService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JoueursCdm(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.joueurs = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'joueursListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JoueursCdm();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.joueurs = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'joueursListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
