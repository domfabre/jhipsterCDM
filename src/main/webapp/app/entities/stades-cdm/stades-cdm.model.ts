import { BaseEntity } from './../../shared';

export class StadesCdm implements BaseEntity {
    constructor(
        public id?: number,
        public stade?: string,
        public ville?: string,
        public capacite?: number,
        public latitude?: number,
        public longitude?: number,
        public idstades?: BaseEntity[],
    ) {
    }
}
