import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { NationsCdm } from './nations-cdm.model';
import { NationsCdmService } from './nations-cdm.service';

@Component({
    selector: 'jhi-nations-cdm-detail',
    templateUrl: './nations-cdm-detail.component.html'
})
export class NationsCdmDetailComponent implements OnInit, OnDestroy {

    nations: NationsCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private nationsService: NationsCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNations();
    }

    load(id) {
        this.nationsService.find(id)
            .subscribe((nationsResponse: HttpResponse<NationsCdm>) => {
                this.nations = nationsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'nationsListModification',
            (response) => this.load(this.nations.id)
        );
    }
}
