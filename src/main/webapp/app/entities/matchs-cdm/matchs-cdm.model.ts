import { BaseEntity } from './../../shared';

export const enum Phases {
    'J1',
    'J2',
    'J3',
    'HF',
    'QF',
    'DF',
    'PF',
    'F'
}

export class MatchsCdm implements BaseEntity {
    constructor(
        public id?: number,
        public match?: string,
        public date?: string,
        public heure?: string,
        public stade?: string,
        public domicile?: boolean,
        public phase?: Phases,
        public idmatches?: BaseEntity[],
        public stadesId?: number,
    ) {
        this.domicile = false;
    }
}
