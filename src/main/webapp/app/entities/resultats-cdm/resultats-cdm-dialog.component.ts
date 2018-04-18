import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ResultatsCdm } from './resultats-cdm.model';
import { ResultatsCdmPopupService } from './resultats-cdm-popup.service';
import { ResultatsCdmService } from './resultats-cdm.service';
import { NationsCdm, NationsCdmService } from '../nations-cdm';
import { MatchsCdm, MatchsCdmService } from '../matchs-cdm';

@Component({
    selector: 'jhi-resultats-cdm-dialog',
    templateUrl: './resultats-cdm-dialog.component.html'
})
export class ResultatsCdmDialogComponent implements OnInit {

    resultats: ResultatsCdm;
    isSaving: boolean;

    nations: NationsCdm[];

    matchs: MatchsCdm[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private resultatsService: ResultatsCdmService,
        private nationsService: NationsCdmService,
        private matchsService: MatchsCdmService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.nationsService.query()
            .subscribe((res: HttpResponse<NationsCdm[]>) => { this.nations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.matchsService.query()
            .subscribe((res: HttpResponse<MatchsCdm[]>) => { this.matchs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.resultats.id !== undefined) {
            this.subscribeToSaveResponse(
                this.resultatsService.update(this.resultats));
        } else {
            this.subscribeToSaveResponse(
                this.resultatsService.create(this.resultats));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ResultatsCdm>>) {
        result.subscribe((res: HttpResponse<ResultatsCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ResultatsCdm) {
        this.eventManager.broadcast({ name: 'resultatsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackNationsById(index: number, item: NationsCdm) {
        return item.id;
    }

    trackMatchsById(index: number, item: MatchsCdm) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-resultats-cdm-popup',
    template: ''
})
export class ResultatsCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private resultatsPopupService: ResultatsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.resultatsPopupService
                    .open(ResultatsCdmDialogComponent as Component, params['id']);
            } else {
                this.resultatsPopupService
                    .open(ResultatsCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
