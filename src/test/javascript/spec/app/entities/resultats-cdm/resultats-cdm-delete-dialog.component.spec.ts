/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ResultatsCdmDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm-delete-dialog.component';
import { ResultatsCdmService } from '../../../../../../main/webapp/app/entities/resultats-cdm/resultats-cdm.service';

describe('Component Tests', () => {

    describe('ResultatsCdm Management Delete Component', () => {
        let comp: ResultatsCdmDeleteDialogComponent;
        let fixture: ComponentFixture<ResultatsCdmDeleteDialogComponent>;
        let service: ResultatsCdmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ResultatsCdmDeleteDialogComponent],
                providers: [
                    ResultatsCdmService
                ]
            })
            .overrideTemplate(ResultatsCdmDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResultatsCdmDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResultatsCdmService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
