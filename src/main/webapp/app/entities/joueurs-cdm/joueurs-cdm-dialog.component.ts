import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JoueursCdm } from './joueurs-cdm.model';
import { JoueursCdmPopupService } from './joueurs-cdm-popup.service';
import { JoueursCdmService } from './joueurs-cdm.service';

@Component({
    selector: 'jhi-joueurs-cdm-dialog',
    templateUrl: './joueurs-cdm-dialog.component.html'
})
export class JoueursCdmDialogComponent implements OnInit {

    joueurs: JoueursCdm;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private joueursService: JoueursCdmService,
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
        if (this.joueurs.id !== undefined) {
            this.subscribeToSaveResponse(
                this.joueursService.update(this.joueurs));
        } else {
            this.subscribeToSaveResponse(
                this.joueursService.create(this.joueurs));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JoueursCdm>>) {
        result.subscribe((res: HttpResponse<JoueursCdm>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JoueursCdm) {
        this.eventManager.broadcast({ name: 'joueursListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-joueurs-cdm-popup',
    template: ''
})
export class JoueursCdmPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private joueursPopupService: JoueursCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.joueursPopupService
                    .open(JoueursCdmDialogComponent as Component, params['id']);
            } else {
                this.joueursPopupService
                    .open(JoueursCdmDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
