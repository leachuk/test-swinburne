#Design Importer

Each subfolder in this location is a individual Design Importer Package.

Contents of subfolder should be zipped into a zip file that will be dropped into Design Importer component on a page in AEM.

## Package List

* adce - Accenture Data Collection Engine test, example Angular app being developed to make forms on pages. Will be used as drop in forms on pages.


## Create Design Importer Package

To create Design Importer you need to create a ZIP with the contents of the Package. Please ensure that OS default files are not included in the package. In the directory of the Package run the following command.

On Mac
```
zip -r design-importer-package.zip . -x "*.DS_Store" -x "__MACOSX" -x "Icon"
```
