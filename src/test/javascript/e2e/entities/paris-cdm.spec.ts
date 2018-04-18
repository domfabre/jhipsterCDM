import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Paris e2e test', () => {

    let navBarPage: NavBarPage;
    let parisDialogPage: ParisDialogPage;
    let parisComponentsPage: ParisComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Parises', () => {
        navBarPage.goToEntity('paris-cdm');
        parisComponentsPage = new ParisComponentsPage();
        expect(parisComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.paris.home.title/);

    });

    it('should load create Paris dialog', () => {
        parisComponentsPage.clickOnCreateButton();
        parisDialogPage = new ParisDialogPage();
        expect(parisDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.paris.home.createOrEditLabel/);
        parisDialogPage.close();
    });

    it('should create and save Parises', () => {
        parisComponentsPage.clickOnCreateButton();
        parisDialogPage.setButInput('5');
        expect(parisDialogPage.getButInput()).toMatch('5');
        parisDialogPage.getJockerInput().isSelected().then((selected) => {
            if (selected) {
                parisDialogPage.getJockerInput().click();
                expect(parisDialogPage.getJockerInput().isSelected()).toBeFalsy();
            } else {
                parisDialogPage.getJockerInput().click();
                expect(parisDialogPage.getJockerInput().isSelected()).toBeTruthy();
            }
        });
        parisDialogPage.setPointInput('5');
        expect(parisDialogPage.getPointInput()).toMatch('5');
        parisDialogPage.joueursSelectLastOption();
        parisDialogPage.idparisSelectLastOption();
        parisDialogPage.save();
        expect(parisDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ParisComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-paris-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ParisDialogPage {
    modalTitle = element(by.css('h4#myParisLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    butInput = element(by.css('input#field_but'));
    jockerInput = element(by.css('input#field_jocker'));
    pointInput = element(by.css('input#field_point'));
    joueursSelect = element(by.css('select#field_joueurs'));
    idparisSelect = element(by.css('select#field_idparis'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setButInput = function(but) {
        this.butInput.sendKeys(but);
    };

    getButInput = function() {
        return this.butInput.getAttribute('value');
    };

    getJockerInput = function() {
        return this.jockerInput;
    };
    setPointInput = function(point) {
        this.pointInput.sendKeys(point);
    };

    getPointInput = function() {
        return this.pointInput.getAttribute('value');
    };

    joueursSelectLastOption = function() {
        this.joueursSelect.all(by.tagName('option')).last().click();
    };

    joueursSelectOption = function(option) {
        this.joueursSelect.sendKeys(option);
    };

    getJoueursSelect = function() {
        return this.joueursSelect;
    };

    getJoueursSelectedOption = function() {
        return this.joueursSelect.element(by.css('option:checked')).getText();
    };

    idparisSelectLastOption = function() {
        this.idparisSelect.all(by.tagName('option')).last().click();
    };

    idparisSelectOption = function(option) {
        this.idparisSelect.sendKeys(option);
    };

    getIdparisSelect = function() {
        return this.idparisSelect;
    };

    getIdparisSelectedOption = function() {
        return this.idparisSelect.element(by.css('option:checked')).getText();
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
