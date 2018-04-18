import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MatchsCdm } from './matchs-cdm.model';
import { MatchsCdmPopupService } from './matchs-cdm-popup.service';
import { MatchsCdmService } from './matchs-cdm.service';

@Component({
    selector: 'jhi-matchs-cdm-delete-dialog',
    templateUrl: './matchs-cdm-delete-dialog.component.html'
})
export class MatchsCdmDeleteDialogComponent {

    matchs: MatchsCdm;

    constructor(
        private matchsService: MatchsCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.matchsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'matchsListModification',
                content: 'Deleted an matchs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-matchs-cdm-delete-popup',
    template: ''
})
export class MatchsCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private matchsPopupService: MatchsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.matchsPopupService
                .open(MatchsCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
