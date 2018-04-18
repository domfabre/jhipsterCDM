import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MatchsCdm } from './matchs-cdm.model';
import { MatchsCdmPopupService } from './matchs-cdm-popup.service';
import { MatchsCdmService } from './matchs-cdm.service';
import { StadesCdm, StadesCdmService } from '../stades-cdm';

@Component({
    selector: 'jhi-matchs-cdm-dialog',
    templateUrl: './matchs-cdm-dialog.component.html'
})
export class MatchsCdmDialogComponent implements OnInit {

    matchs: MatchsCdm;
    isSaving: boolean;

    stades: StadesCdm[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private matchsService: MatchsCdmService,
        private stadesService: StadesCdmService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.stadesService.query()
            .subscribe((res: HttpResponse<StadesCdm[]>) => { this.stades = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.matchs.id !== undefined) {
            this.subscribeToSaveResponse(
                this.matchsService.update(this.matchs));
        } else {
            this.subscribeToSaveResponse(
                this.matchsService.create(this.matchs));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MatchsCdm>>) {
        result.subscribe((res: HttpResponse<MatchsCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MatchsCdm) {
        this.eventManager.broadcast({ name: 'matchsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStadesById(index: number, item: StadesCdm) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-matchs-cdm-popup',
    template: ''
})
export class MatchsCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private matchsPopupService: MatchsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.matchsPopupService
                    .open(MatchsCdmDialogComponent as Component, params['id']);
            } else {
                this.matchsPopupService
                    .open(MatchsCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
