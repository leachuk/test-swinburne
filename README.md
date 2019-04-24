
#Swinburne AEM Parent Project



Please ensure following packages are installed on your AEM instance before deploying this project

* AEM 6.3 Service Pack 1
* ACS Twitter
* ACS Commons
* Adobe Core
* AEM Design Author
* AEM Design Common

This project is a parent project used to manage modules for AEM project.

All branches must be named as follows:
* `master`: The main branch for integration and releases.
    All changes to this branch must be done with pull requests.
* `feature/<JIRA_ISSUE>_<DESCRIPTION>`: All branches for developing user stories.
* `defect/<JIRA_ISSUE>_<DESCRIPTION>`: All branches for developing bugfixes.


To generate keys for accessing Git use this command

```bash
KEYNAME=swinburne; ssh-keygen -t rsa -b 4096 -C "${KEYNAME}" -N '' -f "${KEYNAME}"
```




Use the deploy scripts or use the following command

```bash
mvn -Dvault.useProxy=false -DskipTests clean install -P autoInstallBundle,autoInstallPackage -pl swinburne-core-common,swinburne-core-content,swinburne-core-showcase  -Dcrx.host=localhost
```



Deployment Package is a single package that has all of the dependencies embedded. This allow simpler version management of packages to be deployed.

To use this deployment process run the following to deploy to Dev VM

```bash
./deploy-deploymentpackage
```

or to deploy locally run:

```bash
./deploy-deploymentpackage-local
```

or manually trigger with options you require

```bash
mvn -Dvault.useProxy=false -DskipTests -e -U -P localrepos,deploymentpackage,installdeploymentpackage clean install
```

# Adding new components process

Please follow this process when adding new component to project

1. Copy Component Showcase pages to similar location and replace all references of ```sling:resourceType="aemdesign/components/{group}/{component}"``` with ```sling:resourceType="swinburne/components/{group}/{component}"```
2. Copy Component Tests to similar location and update each test case to point to Swinburne showcase updating this ```String pathSite = "content/swinburne-showcase"``` to ```String pathSite = "content/swinburne-showcase"```
3. Run the tests.

NOTE: if you do not do this you will most likely have your PR declined.
