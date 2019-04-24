package specs.uniquepages.campaign

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class JIESpec extends ComponentSpec {

    String pathPage = "pages/jie"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/page_details"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Adaptive QueryString Auto Fill"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def formID = "aemFormFrame"
        def selector = "#aemFormFrame"
        def selectorContainer = "#columns1"
        def query = "firstname=SampleFirstName&lastname=SampleLastName&bestdescription=BD03.%20Current%20Year%2011%20student"

        when: 'I am on the Unique Experience showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPageWithQuery(language, query)
        report("I am on the Unique Experience showcase page")

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "First Name field should have sample value"
        assert js.exec("return window.document.getElementById('$formID').contentWindow.guideBridge.resolveNode('firstname').value == 'SampleFirstName';")
//        withFrame("aemFormFrame") {
//            takeScreenshot($(".firstname").firstElement(), "First Name field should have sample value")
//            return true
//        }

        and: "Last Name field should have sample value"
        assert js.exec("return window.document.getElementById('$formID').contentWindow.guideBridge.resolveNode('lastname').value == 'SampleLastName';")

        and: "Best Description field should have sample value"
        assert js.exec("return window.document.getElementById('$formID').contentWindow.guideBridge.resolveNode('bestdescription').value == 'BD03. Current Year 11 student';")


    }



}
