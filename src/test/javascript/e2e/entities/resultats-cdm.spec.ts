import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Resultats e2e test', () => {

    let navBarPage: NavBarPage;
    let resultatsDialogPage: ResultatsDialogPage;
    let resultatsComponentsPage: ResultatsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Resultats', () => {
        navBarPage.goToEntity('resultats-cdm');
        resultatsComponentsPage = new ResultatsComponentsPage();
        expect(resultatsComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.resultats.home.title/);

    });

    it('should load create Resultats dialog', () => {
        resultatsComponentsPage.clickOnCreateButton();
        resultatsDialogPage = new ResultatsDialogPage();
        expect(resultatsDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.resultats.home.createOrEditLabel/);
        resultatsDialogPage.close();
    });

    it('should create and save Resultats', () => {
        resultatsComponentsPage.clickOnCreateButton();
        resultatsDialogPage.setButInput('5');
        expect(resultatsDialogPage.getButInput()).toMatch('5');
        resultatsDialogPage.setTabInput('5');
        expect(resultatsDialogPage.getTabInput()).toMatch('5');
        resultatsDialogPage.setJauneInput('5');
        expect(resultatsDialogPage.getJauneInput()).toMatch('5');
        resultatsDialogPage.setRougeInput('5');
        expect(resultatsDialogPage.getRougeInput()).toMatch('5');
        resultatsDialogPage.getDomicileInput().isSelected().then((selected) => {
            if (selected) {
                resultatsDialogPage.getDomicileInput().click();
                expect(resultatsDialogPage.getDomicileInput().isSelected()).toBeFalsy();
            } else {
                resultatsDialogPage.getDomicileInput().click();
                expect(resultatsDialogPage.getDomicileInput().isSelected()).toBeTruthy();
            }
        });
        resultatsDialogPage.nationsSelectLastOption();
        resultatsDialogPage.matchsSelectLastOption();
        resultatsDialogPage.save();
        expect(resultatsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ResultatsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-resultats-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ResultatsDialogPage {
    modalTitle = element(by.css('h4#myResultatsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    butInput = element(by.css('input#field_but'));
    tabInput = element(by.css('input#field_tab'));
    jauneInput = element(by.css('input#field_jaune'));
    rougeInput = element(by.css('input#field_rouge'));
    domicileInput = element(by.css('input#field_domicile'));
    nationsSelect = element(by.css('select#field_nations'));
    matchsSelect = element(by.css('select#field_matchs'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setButInput = function(but) {
        this.butInput.sendKeys(but);
    };

    getButInput = function() {
        return this.butInput.getAttribute('value');
    };

    setTabInput = function(tab) {
        this.tabInput.sendKeys(tab);
    };

    getTabInput = function() {
        return this.tabInput.getAttribute('value');
    };

    setJauneInput = function(jaune) {
        this.jauneInput.sendKeys(jaune);
    };

    getJauneInput = function() {
        return this.jauneInput.getAttribute('value');
    };

    setRougeInput = function(rouge) {
        this.rougeInput.sendKeys(rouge);
    };

    getRougeInput = function() {
        return this.rougeInput.getAttribute('value');
    };

    getDomicileInput = function() {
        return this.domicileInput;
    };
    nationsSelectLastOption = function() {
        this.nationsSelect.all(by.tagName('option')).last().click();
    };

    nationsSelectOption = function(option) {
        this.nationsSelect.sendKeys(option);
    };

    getNationsSelect = function() {
        return this.nationsSelect;
    };

    getNationsSelectedOption = function() {
        return this.nationsSelect.element(by.css('option:checked')).getText();
    };

    matchsSelectLastOption = function() {
        this.matchsSelect.all(by.tagName('option')).last().click();
    };

    matchsSelectOption = function(option) {
        this.matchsSelect.sendKeys(option);
    };

    getMatchsSelect = function() {
        return this.matchsSelect;
    };

    getMatchsSelectedOption = function() {
        return this.matchsSelect.element(by.css('option:checked')).getText();
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
