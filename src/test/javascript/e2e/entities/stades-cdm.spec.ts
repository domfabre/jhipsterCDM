import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Stades e2e test', () => {

    let navBarPage: NavBarPage;
    let stadesDialogPage: StadesDialogPage;
    let stadesComponentsPage: StadesComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Stades', () => {
        navBarPage.goToEntity('stades-cdm');
        stadesComponentsPage = new StadesComponentsPage();
        expect(stadesComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.stades.home.title/);

    });

    it('should load create Stades dialog', () => {
        stadesComponentsPage.clickOnCreateButton();
        stadesDialogPage = new StadesDialogPage();
        expect(stadesDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.stades.home.createOrEditLabel/);
        stadesDialogPage.close();
    });

    it('should create and save Stades', () => {
        stadesComponentsPage.clickOnCreateButton();
        stadesDialogPage.setStadeInput('stade');
        expect(stadesDialogPage.getStadeInput()).toMatch('stade');
        stadesDialogPage.setVilleInput('ville');
        expect(stadesDialogPage.getVilleInput()).toMatch('ville');
        stadesDialogPage.setCapaciteInput('5');
        expect(stadesDialogPage.getCapaciteInput()).toMatch('5');
        stadesDialogPage.setLatitudeInput('5');
        expect(stadesDialogPage.getLatitudeInput()).toMatch('5');
        stadesDialogPage.setLongitudeInput('5');
        expect(stadesDialogPage.getLongitudeInput()).toMatch('5');
        stadesDialogPage.save();
        expect(stadesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class StadesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-stades-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class StadesDialogPage {
    modalTitle = element(by.css('h4#myStadesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    stadeInput = element(by.css('input#field_stade'));
    villeInput = element(by.css('input#field_ville'));
    capaciteInput = element(by.css('input#field_capacite'));
    latitudeInput = element(by.css('input#field_latitude'));
    longitudeInput = element(by.css('input#field_longitude'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setStadeInput = function(stade) {
        this.stadeInput.sendKeys(stade);
    };

    getStadeInput = function() {
        return this.stadeInput.getAttribute('value');
    };

    setVilleInput = function(ville) {
        this.villeInput.sendKeys(ville);
    };

    getVilleInput = function() {
        return this.villeInput.getAttribute('value');
    };

    setCapaciteInput = function(capacite) {
        this.capaciteInput.sendKeys(capacite);
    };

    getCapaciteInput = function() {
        return this.capaciteInput.getAttribute('value');
    };

    setLatitudeInput = function(latitude) {
        this.latitudeInput.sendKeys(latitude);
    };

    getLatitudeInput = function() {
        return this.latitudeInput.getAttribute('value');
    };

    setLongitudeInput = function(longitude) {
        this.longitudeInput.sendKeys(longitude);
    };

    getLongitudeInput = function() {
        return this.longitudeInput.getAttribute('value');
    };

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
