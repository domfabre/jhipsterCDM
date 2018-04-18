import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Joueurs e2e test', () => {

    let navBarPage: NavBarPage;
    let joueursDialogPage: JoueursDialogPage;
    let joueursComponentsPage: JoueursComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Joueurs', () => {
        navBarPage.goToEntity('joueurs-cdm');
        joueursComponentsPage = new JoueursComponentsPage();
        expect(joueursComponentsPage.getTitle())
            .toMatch(/jhipsterCdmApp.joueurs.home.title/);

    });

    it('should load create Joueurs dialog', () => {
        joueursComponentsPage.clickOnCreateButton();
        joueursDialogPage = new JoueursDialogPage();
        expect(joueursDialogPage.getModalTitle())
            .toMatch(/jhipsterCdmApp.joueurs.home.createOrEditLabel/);
        joueursDialogPage.close();
    });

    it('should create and save Joueurs', () => {
        joueursComponentsPage.clickOnCreateButton();
        joueursDialogPage.setJoueurInput('joueur');
        expect(joueursDialogPage.getJoueurInput()).toMatch('joueur');
        joueursDialogPage.setCourrielInput('courriel');
        expect(joueursDialogPage.getCourrielInput()).toMatch('courriel');
        joueursDialogPage.setAvatarInput('avatar');
        expect(joueursDialogPage.getAvatarInput()).toMatch('avatar');
        joueursDialogPage.save();
        expect(joueursDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class JoueursComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-joueurs-cdm div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class JoueursDialogPage {
    modalTitle = element(by.css('h4#myJoueursLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    joueurInput = element(by.css('input#field_joueur'));
    courrielInput = element(by.css('input#field_courriel'));
    avatarInput = element(by.css('input#field_avatar'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setJoueurInput = function(joueur) {
        this.joueurInput.sendKeys(joueur);
    };

    getJoueurInput = function() {
        return this.joueurInput.getAttribute('value');
    };

    setCourrielInput = function(courriel) {
        this.courrielInput.sendKeys(courriel);
    };

    getCourrielInput = function() {
        return this.courrielInput.getAttribute('value');
    };

    setAvatarInput = function(avatar) {
        this.avatarInput.sendKeys(avatar);
    };

    getAvatarInput = function() {
        return this.avatarInput.getAttribute('value');
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
