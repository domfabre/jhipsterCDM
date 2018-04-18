import { BaseEntity } from './../../shared';

export class JoueursCdm implements BaseEntity {
    constructor(
        public id?: number,
        public joueur?: string,
        public courriel?: string,
        public avatar?: string,
        public idjoueurs?: BaseEntity[],
    ) {
    }
}
