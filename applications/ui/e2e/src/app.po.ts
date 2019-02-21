import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl + '') as Promise<any>;
  }

  getHeaderText() {
    return element(by.css('app-root div.header')).getText() as Promise<string>;
  }
}
