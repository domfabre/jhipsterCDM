import { BaseEntity } from './../../shared';

export const enum Groupes {
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H'
}

export class NationsCdm implements BaseEntity {
    constructor(
        public id?: number,
        public nation?: string,
        public confederation?: string,
        public fifa?: number,
        public cdm?: number,
        public groupe?: Groupes,
        public idnations?: BaseEntity[],
    ) {
    }
}
