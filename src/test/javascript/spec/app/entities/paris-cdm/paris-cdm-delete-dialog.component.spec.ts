/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterCdmTestModule } from '../../../test.module';
import { ParisCdmDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm-delete-dialog.component';
import { ParisCdmService } from '../../../../../../main/webapp/app/entities/paris-cdm/paris-cdm.service';

describe('Component Tests', () => {

    describe('ParisCdm Management Delete Component', () => {
        let comp: ParisCdmDeleteDialogComponent;
        let fixture: ComponentFixture<ParisCdmDeleteDialogComponent>;
        let service: ParisCdmService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterCdmTestModule],
                declarations: [ParisCdmDeleteDialogComponent],
                providers: [
                    ParisCdmService
                ]
            })
            .overrideTemplate(ParisCdmDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ParisCdmDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParisCdmService);
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
