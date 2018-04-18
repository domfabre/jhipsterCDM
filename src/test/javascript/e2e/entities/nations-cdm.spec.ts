import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Nations e2e test', () => {

    let navBarPage: NavBarPage;
    let nationsDialogPage: NationsDialogPage;
    let nationsComponentsPage: NationsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Nations', () => {
        navBarPage.goToEntity('nations-cdm');
        nationsComponentsPage = new NationsComponentsPage();
        expect(nationsComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.nations.home.title/);

    });

    it('should load create Nations dialog', () => {
        nationsComponentsPage.clickOnCreateButton();
        nationsDialogPage = new NationsDialogPage();
        expect(nationsDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.nations.home.createOrEditLabel/);
        nationsDialogPage.close();
    });

    it('should create and save Nations', () => {
        nationsComponentsPage.clickOnCreateButton();
        nationsDialogPage.setNationInput('nation');
        expect(nationsDialogPage.getNationInput()).toMatch('nation');
        nationsDialogPage.setConfederationInput('confederation');
        expect(nationsDialogPage.getConfederationInput()).toMatch('confederation');
        nationsDialogPage.setFifaInput('5');
        expect(nationsDialogPage.getFifaInput()).toMatch('5');
        nationsDialogPage.setCdmInput('5');
        expect(nationsDialogPage.getCdmInput()).toMatch('5');
        nationsDialogPage.groupeSelectLastOption();
        nationsDialogPage.save();
        expect(nationsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class NationsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-nations-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NationsDialogPage {
    modalTitle = element(by.css('h4#myNationsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nationInput = element(by.css('input#field_nation'));
    confederationInput = element(by.css('input#field_confederation'));
    fifaInput = element(by.css('input#field_fifa'));
    cdmInput = element(by.css('input#field_cdm'));
    groupeSelect = element(by.css('select#field_groupe'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNationInput = function(nation) {
        this.nationInput.sendKeys(nation);
    };

    getNationInput = function() {
        return this.nationInput.getAttribute('value');
    };

    setConfederationInput = function(confederation) {
        this.confederationInput.sendKeys(confederation);
    };

    getConfederationInput = function() {
        return this.confederationInput.getAttribute('value');
    };

    setFifaInput = function(fifa) {
        this.fifaInput.sendKeys(fifa);
    };

    getFifaInput = function() {
        return this.fifaInput.getAttribute('value');
    };

    setCdmInput = function(cdm) {
        this.cdmInput.sendKeys(cdm);
    };

    getCdmInput = function() {
        return this.cdmInput.getAttribute('value');
    };

    setGroupeSelect = function(groupe) {
        this.groupeSelect.sendKeys(groupe);
    };

    getGroupeSelect = function() {
        return this.groupeSelect.element(by.css('option:checked')).getText();
    };

    groupeSelectLastOption = function() {
        this.groupeSelect.all(by.tagName('option')).last().click();
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
