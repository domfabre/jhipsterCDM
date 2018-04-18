import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { StadesCdm } from './stades-cdm.model';
import { StadesCdmService } from './stades-cdm.service';

@Component({
    selector: 'jhi-stades-cdm-detail',
    templateUrl: './stades-cdm-detail.component.html'
})
export class StadesCdmDetailComponent implements OnInit, OnDestroy {

    stades: StadesCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private stadesService: StadesCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStades();
    }

    load(id) {
        this.stadesService.find(id)
            .subscribe((stadesResponse: HttpResponse<StadesCdm>) => {
                this.stades = stadesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStades() {
        this.eventSubscriber = this.eventManager.subscribe(
            'stadesListModification',
            (response) => this.load(this.stades.id)
        );
    }
}
