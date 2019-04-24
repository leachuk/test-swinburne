Swinburne AEM Commom Components
======================================

This project is for all specific components required for this project.

Please use AEM.Design components as a guide for developing new components.

Deploying
---------

Deploy project to local AEM instance use the deploy scripts or the following command

```bash
mvn -Dvault.useProxy=false -DskipTests clean install -P autoInstallPackage,autoInstallBundle -Dcrx.host=localhost
```


