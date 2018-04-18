import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ParisCdm } from './paris-cdm.model';
import { ParisCdmPopupService } from './paris-cdm-popup.service';
import { ParisCdmService } from './paris-cdm.service';
import { JoueursCdm, JoueursCdmService } from '../joueurs-cdm';
import { ResultatsCdm, ResultatsCdmService } from '../resultats-cdm';

@Component({
    selector: 'jhi-paris-cdm-dialog',
    templateUrl: './paris-cdm-dialog.component.html'
})
export class ParisCdmDialogComponent implements OnInit {

    paris: ParisCdm;
    isSaving: boolean;

    joueurs: JoueursCdm[];

    idparises: ResultatsCdm[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private parisService: ParisCdmService,
        private joueursService: JoueursCdmService,
        private resultatsService: ResultatsCdmService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.joueursService.query()
            .subscribe((res: HttpResponse<JoueursCdm[]>) => { this.joueurs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.resultatsService
            .query({filter: 'paris-is-null'})
            .subscribe((res: HttpResponse<ResultatsCdm[]>) => {
                if (!this.paris.idparisId) {
                    this.idparises = res.body;
                } else {
                    this.resultatsService
                        .find(this.paris.idparisId)
                        .subscribe((subRes: HttpResponse<ResultatsCdm>) => {
                            this.idparises = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.paris.id !== undefined) {
            this.subscribeToSaveResponse(
                this.parisService.update(this.paris));
        } else {
            this.subscribeToSaveResponse(
                this.parisService.create(this.paris));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ParisCdm>>) {
        result.subscribe((res: HttpResponse<ParisCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ParisCdm) {
        this.eventManager.broadcast({ name: 'parisListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackJoueursById(index: number, item: JoueursCdm) {
        return item.id;
    }

    trackResultatsById(index: number, item: ResultatsCdm) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-paris-cdm-popup',
    template: ''
})
export class ParisCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private parisPopupService: ParisCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.parisPopupService
                    .open(ParisCdmDialogComponent as Component, params['id']);
            } else {
                this.parisPopupService
                    .open(ParisCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
