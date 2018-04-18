import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NationsCdm } from './nations-cdm.model';
import { NationsCdmPopupService } from './nations-cdm-popup.service';
import { NationsCdmService } from './nations-cdm.service';

@Component({
    selector: 'jhi-nations-cdm-delete-dialog',
    templateUrl: './nations-cdm-delete-dialog.component.html'
})
export class NationsCdmDeleteDialogComponent {

    nations: NationsCdm;

    constructor(
        private nationsService: NationsCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nationsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'nationsListModification',
                content: 'Deleted an nations'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nations-cdm-delete-popup',
    template: ''
})
export class NationsCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private nationsPopupService: NationsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.nationsPopupService
                .open(NationsCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
