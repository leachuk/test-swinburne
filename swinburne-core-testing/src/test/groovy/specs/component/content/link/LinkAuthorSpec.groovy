package specs.component.content.link

import support.page.ui.touch.TouchUIEditor
import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class LinkAuthorSpec extends ComponentSpec {

    String pathPage = "component/content/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    def cleanupSpec() {
        analyzeLog()
    }

    def "Authoring of Component"() {

        given: "Component has already been added to component showcase page"
        def selector = "#link1"

        when: "I am on the Component showcase page"
        TouchUIEditor page = waitForTouchUIPage(language)

        then: "The component should be on the showcase page"
        waitFor { withFrame(TouchUIEditor.PAGE_FRAME_CONTENT) { $(selector) } }

        and: "All dialogs are closed"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("All dialogs are closed")

        when: "I open the dialog box"
        page.Editor.showDialog(compileComponentPath())

        then: "I should be able to open component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == true
        report("I should be able to open component author dialog")

        when: "I close the dialog box"
        page.Editor.closeDialog(compileComponentPath())

        then: "I should be able to save and close component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("I should be able to save and close component author dialog")
    }

}
