import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MatchsCdm } from './matchs-cdm.model';
import { MatchsCdmService } from './matchs-cdm.service';

@Component({
    selector: 'jhi-matchs-cdm-detail',
    templateUrl: './matchs-cdm-detail.component.html'
})
export class MatchsCdmDetailComponent implements OnInit, OnDestroy {

    matchs: MatchsCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private matchsService: MatchsCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMatchs();
    }

    load(id) {
        this.matchsService.find(id)
            .subscribe((matchsResponse: HttpResponse<MatchsCdm>) => {
                this.matchs = matchsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMatchs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'matchsListModification',
            (response) => this.load(this.matchs.id)
        );
    }
}
