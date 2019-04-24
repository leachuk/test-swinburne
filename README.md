
#Swinburne AEM Parent Project



Please ensure following packages are installed on your AEM instance before deploying this project

* AEM 6.3 Service Pack 1
* ACS Twitter
* ACS Commons
* Adobe Core
* AEM Design Author
* AEM Design Common

This project is a parent project used to manage modules for AEM project.


This project uses git submodule's, please use the following command to clone project with ith submodules initially

```bash
git clone --recurse-submodules ssh://git@gitlab.com/swinburne/swinburne-parent.git
```

or

```bash
git clone ssh://git@gitlab.com/swinburne/swinburne-parent.git
git submodule update --recursive --remote --init
```


When creating new submodules use following command to add it to parent project

```bash
git submodule add ssh://git@gitlab.com/swinburne/swinburne-core-common.git swinburne-core-common
```

After adding new submodules do a submodule update


After adding new submodule update and pull

```bash
git submodule update --recursive --remote --init
```


Push all submodules

```bash
git submodule foreach git push
```


Git commit rules has been setup to require GPG signed commits please update your system or repos with correct GPG key.

If you are using different account to commit code to repos, update each of the repos with your required account

```bash
git submodule foreach 'git config user.signingkey {your key id}'
git submodule foreach 'git config user.email {your email}'
```


If you commit using wrong account you can udate your commits using following command

```bash
git commit --amend --author="{your full name} <{your email}>"
```


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
