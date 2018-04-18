import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NationsCdm } from './nations-cdm.model';
import { NationsCdmPopupService } from './nations-cdm-popup.service';
import { NationsCdmService } from './nations-cdm.service';

@Component({
    selector: 'jhi-nations-cdm-dialog',
    templateUrl: './nations-cdm-dialog.component.html'
})
export class NationsCdmDialogComponent implements OnInit {

    nations: NationsCdm;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private nationsService: NationsCdmService,
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
        if (this.nations.id !== undefined) {
            this.subscribeToSaveResponse(
                this.nationsService.update(this.nations));
        } else {
            this.subscribeToSaveResponse(
                this.nationsService.create(this.nations));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<NationsCdm>>) {
        result.subscribe((res: HttpResponse<NationsCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: NationsCdm) {
        this.eventManager.broadcast({ name: 'nationsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-nations-cdm-popup',
    template: ''
})
export class NationsCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private nationsPopupService: NationsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.nationsPopupService
                    .open(NationsCdmDialogComponent as Component, params['id']);
            } else {
                this.nationsPopupService
                    .open(NationsCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
