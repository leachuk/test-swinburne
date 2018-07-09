package specs.component.details.contactdetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContactDetailsPublishSpec extends ComponentSpec {

    String pathPage = "component/details/contact-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/contactdetails"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component variant Default without included components in #viewport.label")
    def "Functionality of Component variant Default without included components"() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Contact Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contact-details1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has Breadcrumb hidden"
        assert $("${selector} .breadcrumb").isEmpty() == true

        and: "Has Toolbar hidden"
        assert $("${selector} .navbar").isEmpty() == true

        and: "Has Parsys hidden"
        assert $("${selector} .text").isEmpty() == true

        and: "Has Image with Alt Title"
        assert $("${selector} img").attr("alt").trim() == "Author: Max Barrass"

        and: "Has Image with Page Image as Thumbnail from Uploaded Image"
        assert $("${selector} img").attr("src").contains("/content/swinburne-showcase/en/component/details/contact-details.thumb.319.319.png")

        and: "Has Title line with content"
        assert $("${selector} div.title").text().trim() == "Author: Max Barrass"

        and: "Has Description line with content"
        assert $("${selector} div.description").text().trim() == "Adobe Practice lead for Isobar max.barrass@isobar.com"

        and: "Has Description has field jobTitle"
        assert $("${selector} div.description [itemprop=jobTitle]").text().trim() == "Adobe Practice lead"

        and: "Has Description has field employee"
        assert $("${selector} div.description [itemprop=employee]").text().trim() == "Isobar"

        and: "Has Description has field email"
        assert $("${selector} div.description [itemprop=email]").text().trim() == "max.barrass@isobar.com"


        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component variant Default Blank without included components in #viewport.label")
    def "Functionality of Component variant Default Blank without included components"() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Contact Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contact-details2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has Breadcrumb hidden"
        assert $("${selector} .breadcrumb").isEmpty() == true

        and: "Has Toolbar hidden"
        assert $("${selector} .navbar").isEmpty() == true

        and: "Has Parsys hidden"
        assert $("${selector} .text").isEmpty() == true

        and: "Has Image with Alt Title"
        assert $("${selector} img").attr("alt").trim() == "Contact Details"

        and: "Has Image with Page Image as Thumbnail"
        assert $("${selector} img").attr("src").contains("/content/swinburne-showcase/en/component/details/contact-details.thumb.319.319.png")

        and: "Has Title line with content"
        assert $("${selector} div.title").text().trim() == "Contact Details"

        and: "Has Description should be empty"
        assert $("${selector} div.description").text().isEmpty()

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component variant Hidden in #viewport.label")
    def "Functionality of Component variant Default Hidden"() {

        given: '>the page hierarchy is created as "Components" > "Details" > "Contact Details"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contact-details3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        and: "Component should have no content"
        assert $("${selector}").text() == ""

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        where:
        viewport << getViewPorts()
    }

}
