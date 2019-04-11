package src.test.groovy.specs.component.analytics

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

public class AnalyticsLaunchPublishSpec  extends ComponentSpec {

    String pathPage = "component/analytics/launch"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/page-details"

    def setupSpec() {
        loginAsAdmin()
    }

    /**
     * verify if
     * ``<script src="//assets.adobedtm.com/launch-ENff3603b5bce848df8b05b43e547e1dd8.min.js"> </script>  ``
     *
     * is included in all templates.
     */



    /**
     * /content/swinburne-showcase/en/templates/base-page
     */
    @Unroll("Functionality of Data Layer in #viewport.label")
    def "Include launch into page headers template base-page"() {

        given: '>the page hierarchy is created as "Components" > "Analytics" > "Launch"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagedetails1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The page should have digitalData object defined'
        assert js.exec("return window.digitalData != undefined;")


        where:
        viewport << getViewPorts()
    }

    /**
     * /content/swinburne-showcase/en/templates/one-column-v2
     */
    def "Include launch into page headers template one-column-v2"() {

        given: '>the page hierarchy is created as "Components" > "Analytics" > "Launch"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagedetails1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The page should have digitalData object defined'
        assert js.exec("return window.digitalData != undefined;")


        where:
        viewport << getViewPorts()
    }


}
