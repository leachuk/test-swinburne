---
state: in-progress
title: Button
---
Link component

This component can be used within the pattern library by defining the following:

```
{{> components-button:btn-primary|btn-lg(
  custom-button: true,
  href: "https://www.google.com.au",
  label: "My Super Awesome Button"
)}}
```

The `custom` property is important here as it lets the `Button` component know that we don't want the default PL button list but rather a single customisable button.


Component for creating links (calls to action) on a page.
Call to Acton can be a Link or a Button.

* **Vendor**: [AEM.Design](http://aem.design)
* **Version**: v1
* **Compatibility**: AEM 6.3
* **Status**: production-ready
* **Showcase**: [/content/aemdesign-showcase/en/component/content/link.html](/content/aemdesign-showcase/en/component/content/link.html?wcmmode=disabled)
* **Local Code**: /crx/de/#/apps/aemdesign/components/content/link
* **Source**: [gitlab/aemdesign](https://gitlab.com/aem.design/aemdesign-aem-common/tree/master/src/main/content/jcr_root/apps/aemdesign/components/content/link)

* Provides support for anchor and button elements
