import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Matchs e2e test', () => {

    let navBarPage: NavBarPage;
    let matchsDialogPage: MatchsDialogPage;
    let matchsComponentsPage: MatchsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Matchs', () => {
        navBarPage.goToEntity('matchs-cdm');
        matchsComponentsPage = new MatchsComponentsPage();
        expect(matchsComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.matchs.home.title/);

    });

    it('should load create Matchs dialog', () => {
        matchsComponentsPage.clickOnCreateButton();
        matchsDialogPage = new MatchsDialogPage();
        expect(matchsDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.matchs.home.createOrEditLabel/);
        matchsDialogPage.close();
    });

    it('should create and save Matchs', () => {
        matchsComponentsPage.clickOnCreateButton();
        matchsDialogPage.setMatchInput('match');
        expect(matchsDialogPage.getMatchInput()).toMatch('match');
        matchsDialogPage.setDateInput('date');
        expect(matchsDialogPage.getDateInput()).toMatch('date');
        matchsDialogPage.setHeureInput('heure');
        expect(matchsDialogPage.getHeureInput()).toMatch('heure');
        matchsDialogPage.setStadeInput('stade');
        expect(matchsDialogPage.getStadeInput()).toMatch('stade');
        matchsDialogPage.getDomicileInput().isSelected().then((selected) => {
            if (selected) {
                matchsDialogPage.getDomicileInput().click();
                expect(matchsDialogPage.getDomicileInput().isSelected()).toBeFalsy();
            } else {
                matchsDialogPage.getDomicileInput().click();
                expect(matchsDialogPage.getDomicileInput().isSelected()).toBeTruthy();
            }
        });
        matchsDialogPage.phaseSelectLastOption();
        matchsDialogPage.stadesSelectLastOption();
        matchsDialogPage.save();
        expect(matchsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class MatchsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-matchs-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MatchsDialogPage {
    modalTitle = element(by.css('h4#myMatchsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    matchInput = element(by.css('input#field_match'));
    dateInput = element(by.css('input#field_date'));
    heureInput = element(by.css('input#field_heure'));
    stadeInput = element(by.css('input#field_stade'));
    domicileInput = element(by.css('input#field_domicile'));
    phaseSelect = element(by.css('select#field_phase'));
    stadesSelect = element(by.css('select#field_stades'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setMatchInput = function(match) {
        this.matchInput.sendKeys(match);
    };

    getMatchInput = function() {
        return this.matchInput.getAttribute('value');
    };

    setDateInput = function(date) {
        this.dateInput.sendKeys(date);
    };

    getDateInput = function() {
        return this.dateInput.getAttribute('value');
    };

    setHeureInput = function(heure) {
        this.heureInput.sendKeys(heure);
    };

    getHeureInput = function() {
        return this.heureInput.getAttribute('value');
    };

    setStadeInput = function(stade) {
        this.stadeInput.sendKeys(stade);
    };

    getStadeInput = function() {
        return this.stadeInput.getAttribute('value');
    };

    getDomicileInput = function() {
        return this.domicileInput;
    };
    setPhaseSelect = function(phase) {
        this.phaseSelect.sendKeys(phase);
    };

    getPhaseSelect = function() {
        return this.phaseSelect.element(by.css('option:checked')).getText();
    };

    phaseSelectLastOption = function() {
        this.phaseSelect.all(by.tagName('option')).last().click();
    };
    stadesSelectLastOption = function() {
        this.stadesSelect.all(by.tagName('option')).last().click();
    };

    stadesSelectOption = function(option) {
        this.stadesSelect.sendKeys(option);
    };

    getStadesSelect = function() {
        return this.stadesSelect;
    };

    getStadesSelectedOption = function() {
        return this.stadesSelect.element(by.css('option:checked')).getText();
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
