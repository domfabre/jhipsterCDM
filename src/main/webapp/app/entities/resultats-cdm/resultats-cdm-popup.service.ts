import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ResultatsCdm } from './resultats-cdm.model';
import { ResultatsCdmService } from './resultats-cdm.service';

@Injectable()
export class ResultatsCdmPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private resultatsService: ResultatsCdmService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.resultatsService.find(id)
                    .subscribe((resultatsResponse: HttpResponse<ResultatsCdm>) => {
                        const resultats: ResultatsCdm = resultatsResponse.body;
                        this.ngbModalRef = this.resultatsModalRef(component, resultats);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.resultatsModalRef(component, new ResultatsCdm());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    resultatsModalRef(component: Component, resultats: ResultatsCdm): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.resultats = resultats;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
