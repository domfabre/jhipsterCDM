import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JoueursCdm } from './joueurs-cdm.model';
import { JoueursCdmPopupService } from './joueurs-cdm-popup.service';
import { JoueursCdmService } from './joueurs-cdm.service';

@Component({
    selector: 'jhi-joueurs-cdm-delete-dialog',
    templateUrl: './joueurs-cdm-delete-dialog.component.html'
})
export class JoueursCdmDeleteDialogComponent {

    joueurs: JoueursCdm;

    constructor(
        private joueursService: JoueursCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.joueursService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'joueursListModification',
                content: 'Deleted an joueurs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-joueurs-cdm-delete-popup',
    template: ''
})
export class JoueursCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private joueursPopupService: JoueursCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.joueursPopupService
                .open(JoueursCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
