package specs.component.lists.eventlist

import spock.lang.Stepwise
import support.ComponentSpec
import support.page.ui.touch.TouchUIEditor

@Stepwise
class EventListAuthorSpec extends ComponentSpec {

    String pathPage = "component/lists/event-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/eventlist"

    def setupSpec() {
        loginAsAdmin()
    }

    def cleanupSpec() {
        analyzeLog()
    }

    def "Authoring of Component"() {

        given: "Component has already been inserted"
        def selector = "#eventlist1"

        when: "I am on the Component showcase page"
        TouchUIEditor page = waitForTouchUIPage(language)

        then: "The component should be on showcase page"
        waitFor { withFrame(TouchUIEditor.PAGE_FRAME_CONTENT) { $(selector) } }

        and: "All dialogs are closed"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("All dialogs are closed")

        and: "I open the dialog box"
        page.Editor.showDialog(compileComponentPath())

        then: "I should be able to see component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == true
        report("I should be able to see component author dialog")

        when: "I close the dialog box"
        page.Editor.closeDialog(compileComponentPath())

        then: "I should be able to close component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("I should be able to close component author dialog")
    }

}
