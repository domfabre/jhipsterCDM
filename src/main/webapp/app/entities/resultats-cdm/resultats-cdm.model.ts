import { BaseEntity } from './../../shared';

export class ResultatsCdm implements BaseEntity {
    constructor(
        public id?: number,
        public but?: number,
        public tab?: number,
        public jaune?: number,
        public rouge?: number,
        public domicile?: boolean,
        public nationsId?: number,
        public matchsId?: number,
    ) {
        this.domicile = false;
    }
}
