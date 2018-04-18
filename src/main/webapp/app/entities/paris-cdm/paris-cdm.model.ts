import { BaseEntity } from './../../shared';

export class ParisCdm implements BaseEntity {
    constructor(
        public id?: number,
        public but?: number,
        public jocker?: boolean,
        public point?: number,
        public joueursId?: number,
        public idparisId?: number,
    ) {
        this.jocker = false;
    }
}
