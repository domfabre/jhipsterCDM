import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ResultatsCdm } from './resultats-cdm.model';
import { ResultatsCdmPopupService } from './resultats-cdm-popup.service';
import { ResultatsCdmService } from './resultats-cdm.service';

@Component({
    selector: 'jhi-resultats-cdm-delete-dialog',
    templateUrl: './resultats-cdm-delete-dialog.component.html'
})
export class ResultatsCdmDeleteDialogComponent {

    resultats: ResultatsCdm;

    constructor(
        private resultatsService: ResultatsCdmService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resultatsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'resultatsListModification',
                content: 'Deleted an resultats'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resultats-cdm-delete-popup',
    template: ''
})
export class ResultatsCdmDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private resultatsPopupService: ResultatsCdmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.resultatsPopupService
                .open(ResultatsCdmDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
