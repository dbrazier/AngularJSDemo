'use strict';

describe('PaymentTransaction e2e test', function () {

    var username = element(by.id('username'));
    var password = element(by.id('password'));
    var entityMenu = element(by.id('entity-menu'));
    var accountMenu = element(by.id('account-menu'));
    var login = element(by.id('login'));
    var logout = element(by.id('logout'));

    beforeAll(function () {
        browser.get('/');

        accountMenu.click();
        login.click();

        username.sendKeys('admin');
        password.sendKeys('admin');
        element(by.css('button[type=submit]')).click();
    });

    it('should load PaymentTransactions', function () {
        entityMenu.click();
        element(by.css('[ui-sref="payment-transaction"]')).click().then(function() {
            expect(element.all(by.css('h2')).first().getText()).toMatch(/Payment Transactions/);
        });
    });

    it('should load create PaymentTransaction dialog', function () {
        element(by.css('[ui-sref="payment-transaction.new"]')).click().then(function() {
            expect(element(by.css('h4.modal-title')).getAttribute("translate")).toMatch(/mosaicApp.paymentTransaction.home.createOrEditLabel/);
            element(by.css('button.close')).click();
        });
    });

    afterAll(function () {
        accountMenu.click();
        logout.click();
    });
});
