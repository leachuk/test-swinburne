
mvn -D"skipTests=true" clean install -P autoInstallPackage,deploymentpackage,buildSwinburneGlobal,buildSwinburneMicrosites,buildSwinburneStyleguide -D"crx.host=localhost"
