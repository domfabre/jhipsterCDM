import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ParisCdm } from './paris-cdm.model';
import { ParisCdmService } from './paris-cdm.service';

@Component({
    selector: 'jhi-paris-cdm-detail',
    templateUrl: './paris-cdm-detail.component.html'
})
export class ParisCdmDetailComponent implements OnInit, OnDestroy {

    paris: ParisCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private parisService: ParisCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInParises();
    }

    load(id) {
        this.parisService.find(id)
            .subscribe((parisResponse: HttpResponse<ParisCdm>) => {
                this.paris = parisResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInParises() {
        this.eventSubscriber = this.eventManager.subscribe(
            'parisListModification',
            (response) => this.load(this.paris.id)
        );
    }
}
