declare var require: {
  <T>(path: string): T;
  (paths: string[], callback: (...modules: any[]) => void): void;
  ensure: (
    paths: string[],
    callback: (require: <T>(path: string) => T) => void
  ) => void;
}

declare module '*.scss' {
  const content: { [className: string]: string };
  export = content;
}

declare module 'owl.carousel';

declare interface Window { CQ: any; }

interface Element {
  matchesSelector(selectors: string): boolean;
  msMatchesSelector(selectors: string): boolean;
  mozMatchesSelector(selectors: string): boolean;
  oMatchesSelector(selectors: string): boolean;
}

declare function ObjectFitImages(): void;

declare type FormInputCallback = (($target: JQuery, $input: JQuery, event: JQuery.TriggeredEvent) => boolean) | void;

declare type SubscriberTree = {
  default: SubscriberHandler,
}

declare type SubscriberHandler = (event: JQuery.Event, originalEvent: JQuery.TriggeredEvent, type: string) => void;
