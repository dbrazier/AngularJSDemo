'use strict';

describe('Search e2e test', function () {

    var username = element(by.id('username'));
    var password = element(by.id('password'));
    var entityMenu = element(by.id('entity-menu'));
    var accountMenu = element(by.id('account-menu'));
    var login = element(by.id('login'));
    var logout = element(by.id('logout'));
    var caseIdField = element(by.id('field_caseId'));
    var accountNoField = element(by.id('field_accountNo'));
    var gfcIdField = element(by.id('field_gfcId'));
    var caseId = 'S-160919-000005';
    var accountNo = '103180015';
    var gfcId = '1013495994';

    beforeAll(function () {
        browser.get('/');

        accountMenu.click();
        login.click();

        username.sendKeys('admin');
        password.sendKeys('admin');
        element(by.css('button[type=submit]')).click();
    });

    it('should load Searches', function () {
        entityMenu.click();
        element(by.css('[ui-sref="search"]')).click().then(function() {
            expect(element(by.css('h4.modal-title')).getText()).toMatch(/Search by Case Id, Account No or Gfc Id/);
        });
    });

    it('should be able to Search by Case Id', function () {
        caseIdField.sendKeys(caseId);
        element(by.css('button[type=submit]')).click();
        expect(element(by.id('caseId')).getText()).toMatch(caseId);
    });

    it('should be able to Search by Account No', function () {
        //go back to search screen
        element(by.css('button[type=submit]')).click();
        caseIdField.clear();

        accountNoField.sendKeys(accountNo);
        element(by.css('button[type=submit]')).click();
        expect(element(by.id('accountNo')).getText()).toMatch(accountNo);
    });

    it('should be able to Search by Gfc Id', function () {
        //go back to search screen
        element(by.css('button[type=submit]')).click();
        accountNoField.clear();

        gfcIdField.sendKeys(gfcId);
        element(by.css('button[type=submit]')).click();
        expect(element.all(by.css('h4.text-primary')).first().getText()).toMatch(gfcId);
    });

    it('should open Rate History', function () {
        element(by.css('[ui-sref="rate-history"]')).click().then(function() {
            expect(element.all(by.css('h4')).first().getText()).toMatch(/Historical Interest Rate/);
            element(by.css('button.close')).click();
        });
    });

    it('should open Statement Selector', function () {
        element(by.css('[ui-sref="statement-select"]')).click().then(function() {
            expect(element.all(by.css('h4')).first().getText()).toMatch(/Statement Selector/);
            element(by.css('button.close')).click();
        });
    });

    it('should open Payment File Search', function () {
        element(by.css('[ui-sref="payment-search"]')).click().then(function() {
            expect(element.all(by.css('h4')).first().getText()).toMatch(/Payment File Search/);
            element(by.css('button.close')).click();
        });
    });

    it('should expand Failed File', function () {
        element.all(by.css('button.glyphicon-arrow-down')).first().click().then(function() {
            expect(element(by.css('thead.embededTableHead')).getText()).toMatch(/Txn ID/);
        });
    });


    afterAll(function () {
        accountMenu.click();
        logout.click();
    });
});
