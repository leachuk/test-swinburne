Swinburne AEM Base Content
=================================

This package contains all of the initial content that is deployed to new AEM instances.


Deploying
---------

Use the deploy scripts or use the following command

```bash
mvn -Dvault.useProxy=false -DskipTests verify -P turnoffdamworkflow -Dcrx.host=localhost
mvn -Dvault.useProxy=false -DskipTests clean install -P autoInstallPackage -Dcrx.host=localhost
mvn -Dvault.useProxy=false -DskipTests verify -P turnondamworkflow -Dcrx.host=localhost
```





