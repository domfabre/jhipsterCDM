import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StadesCdm } from './stades-cdm.model';
import { StadesCdmPopupService } from './stades-cdm-popup.service';
import { StadesCdmService } from './stades-cdm.service';

@Component({
    selector: 'jhi-stades-cdm-delete-dialog',
    templateUrl: './stades-cdm-delete-dialog.component.html'
})
export class StadesCdmDeleteDialogComponent {

    stades: StadesCdm;

    constructor(
        private stadesService: StadesCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stadesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'stadesListModification',
                content: 'Deleted an stades'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stades-cdm-delete-popup',
    template: ''
})
export class StadesCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private stadesPopupService: StadesCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.stadesPopupService
                .open(StadesCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
