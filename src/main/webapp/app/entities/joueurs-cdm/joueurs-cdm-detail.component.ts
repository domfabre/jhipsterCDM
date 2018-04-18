import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { JoueursCdm } from './joueurs-cdm.model';
import { JoueursCdmService } from './joueurs-cdm.service';

@Component({
    selector: 'jhi-joueurs-cdm-detail',
    templateUrl: './joueurs-cdm-detail.component.html'
})
export class JoueursCdmDetailComponent implements OnInit, OnDestroy {

    joueurs: JoueursCdm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private joueursService: JoueursCdmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJoueurs();
    }

    load(id) {
        this.joueursService.find(id)
            .subscribe((joueursResponse: HttpResponse<JoueursCdm>) => {
                this.joueurs = joueursResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJoueurs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'joueursListModification',
            (response) => this.load(this.joueurs.id)
        );
    }
}
