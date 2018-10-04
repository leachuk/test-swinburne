package specs.component.details.eventdetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class EventDetailsPublishSpec extends ComponentSpec {

    String pathPage = "component/details/event-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/event_details"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Event Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventdetails1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Should have sample heading"
        assert $("$selector [itemprop='name']").text().trim().toLowerCase().equals("Event Details".toLowerCase())

        and: "Has Description line with content"
        assert $("${selector} div.description").text().trim() == "Event to showcase all events"

        and: "Has Description line with content"
        assert $("${selector} div.card-subtitle").text().trim() == "Tue 2 October to Tue 2 October"

        and: "Has Description line with content"
        assert $("${selector} div.card-date").text().trim().toLowerCase() == "2pm to 2pm"

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Hidden in #viewport.label")
    def "Functionality of Component Variant: Hidden "() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Event Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventdetails2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Component should be empty"
        assert $("${selector} > *").isEmpty() == true

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Default with Formatting in #viewport.label")
    def "Functionality of Component Variant: Default with Formatting "() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Event Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventdetails3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Should have sample heading"
        assert $("$selector [itemprop='name']").text().trim().toLowerCase().equals("Event Details".toLowerCase())

        and: "Has Description line with content"
        assert $("${selector} div.description").text().trim() == "Event to showcase all events"

        and: "Has Description line with content"
        assert $("${selector} div.card-subtitle").text().trim() == "Mon 1 October to Fri 5 October"

        and: "Has Description line with content"
        assert $("${selector} div.card-date").text().trim().toLowerCase() == "9am9am"


        where:
        viewport << getViewPorts()
    }


}
