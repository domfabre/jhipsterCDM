import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StadesCdm } from './stades-cdm.model';
import { StadesCdmPopupService } from './stades-cdm-popup.service';
import { StadesCdmService } from './stades-cdm.service';

@Component({
    selector: 'jhi-stades-cdm-dialog',
    templateUrl: './stades-cdm-dialog.component.html'
})
export class StadesCdmDialogComponent implements OnInit {

    stades: StadesCdm;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private stadesService: StadesCdmService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.stades.id !== undefined) {
            this.subscribeToSaveResponse(
                this.stadesService.update(this.stades));
        } else {
            this.subscribeToSaveResponse(
                this.stadesService.create(this.stades));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StadesCdm>>) {
        result.subscribe((res: HttpResponse<StadesCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StadesCdm) {
        this.eventManager.broadcast({ name: 'stadesListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-stades-cdm-popup',
    template: ''
})
export class StadesCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private stadesPopupService: StadesCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.stadesPopupService
                    .open(StadesCdmDialogComponent as Component, params['id']);
            } else {
                this.stadesPopupService
                    .open(StadesCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
