import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ResultatsCdm } from './resultats-cdm.model';
import { ResultatsCdmService } from './resultats-cdm.service';

@Component({
    selector: 'jhi-resultats-cdm-detail',
    templateUrl: './resultats-cdm-detail.component.html'
})
export class ResultatsCdmDetailComponent implements OnInit, OnDestroy {

    resultats: ResultatsCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private resultatsService: ResultatsCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInResultats();
    }

    load(id) {
        this.resultatsService.find(id)
            .subscribe((resultatsResponse: HttpResponse<ResultatsCdm>) => {
                this.resultats = resultatsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInResultats() {
        this.eventSubscriber = this.eventManager.subscribe(
            'resultatsListModification',
            (response) => this.load(this.resultats.id)
        );
    }
}
