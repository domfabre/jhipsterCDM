import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ParisCdm } from './paris-cdm.model';
import { ParisCdmPopupService } from './paris-cdm-popup.service';
import { ParisCdmService } from './paris-cdm.service';

@Component({
    selector: 'jhi-paris-cdm-delete-dialog',
    templateUrl: './paris-cdm-delete-dialog.component.html'
})
export class ParisCdmDeleteDialogComponent {

    paris: ParisCdm;

    constructor(
        private parisService: ParisCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.parisService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'parisListModification',
                content: 'Deleted an paris'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-paris-cdm-delete-popup',
    template: ''
})
export class ParisCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private parisPopupService: ParisCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.parisPopupService
                .open(ParisCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
