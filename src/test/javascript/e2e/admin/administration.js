'use strict';

describe('administration', function () {

    var username = element(by.id('username'));
    var password = element(by.id('password'));
    var accountMenu = element(by.id('account-menu'));
    var adminMenu = element(by.id('admin-menu'));
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

    beforeEach(function () {
        adminMenu.click();
    });

    it('should load user management', function () {
        element(by.css('[ui-sref="user-management"]')).click();
        expect(element.all(by.css('h2')).first().getText()).toMatch(/Users/);
        //expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/userManagement.home.title/);

    });

    it('should load metrics', function () {
        element(by.css('[ui-sref="jhi-metrics"]')).click();
        expect(element.all(by.css('h2')).first().getText()).toMatch(/Metrics/);
        //expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/metrics.title/);
    });

    // it('should load health', function () {
    //     element(by.css('[ui-sref="jhi-health"]')).click();
    //     expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/health.title/);
    // });
    //
    // it('should load configuration', function () {
    //     element(by.css('[ui-sref="jhi-configuration"]')).click();
    //     expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/configuration.title/);
    // });
    //
    // it('should load audits', function () {
    //     element(by.css('[ui-sref="audits"]')).click();
    //     expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/audits.title/);
    // });
    //
    // it('should load logs', function () {
    //     element(by.css('[ui-sref="logs"]')).click();
    //     expect(element.all(by.css('h2')).first().getAttribute("translate")).toMatch(/logs.title/);
    // });

    afterAll(function () {
        accountMenu.click();
        logout.click();
    });
});
