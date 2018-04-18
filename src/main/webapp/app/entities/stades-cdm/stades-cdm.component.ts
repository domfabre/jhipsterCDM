import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StadesCdm } from './stades-cdm.model';
import { StadesCdmService } from './stades-cdm.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-stades-cdm',
    templateUrl: './stades-cdm.component.html'
})
export class StadesCdmComponent implements OnInit, OnDestroy {
stades: StadesCdm[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private stadesService: StadesCdmService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.stadesService.query().subscribe(
            (res: HttpResponse<StadesCdm[]>) => {
                this.stades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStades();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StadesCdm) {
        return item.id;
    }
    registerChangeInStades() {
        this.eventSubscriber = this.eventManager.subscribe('stadesListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
